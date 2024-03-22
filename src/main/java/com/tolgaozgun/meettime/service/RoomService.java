package com.tolgaozgun.meettime.service;

import com.tolgaozgun.meettime.dto.*;
import com.tolgaozgun.meettime.dto.request.room.*;
import com.tolgaozgun.meettime.entity.*;
import com.tolgaozgun.meettime.entity.enums.InvitationStatus;
import com.tolgaozgun.meettime.entity.enums.RoomType;
import com.tolgaozgun.meettime.entity.enums.ScheduleType;
import com.tolgaozgun.meettime.exception.*;
import com.tolgaozgun.meettime.mapper.RoomMapper;
import com.tolgaozgun.meettime.repository.*;
import com.tolgaozgun.meettime.utils.CodeUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomService {

    private final AuthService authService;

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserScheduleTimesRepository userScheduleTimesRepository;
    private final UserScheduleRepository userScheduleRepository;
    private final RoomInvitationRepository roomInvitationRepository;

    private final PasswordEncoder passwordEncoder;

    public RoomDto createRoom(ReqCreateRoom reqCreateRoom){
        User user = authService.getCurrentUserEntity();

        String name = reqCreateRoom.getName();
        String description = reqCreateRoom.getDescription();
        RoomType roomType = reqCreateRoom.getRoomType();

        List<User> moderators = new ArrayList<>();


        ReqRoomSchedule roomScheduleReq = reqCreateRoom.getSchedule();
        ScheduleType scheduleType = roomScheduleReq.getRoomType();
        int minuteIntervals = roomScheduleReq.getMinuteIntervals();
        int notBeforeHour = roomScheduleReq.getNotBeforeHour();
        int notAfterHour = roomScheduleReq.getNotAfterHour();
        String startDateString = roomScheduleReq.getStartDate();
        String endDateString = roomScheduleReq.getEndDate();
        String timeZoneString = roomScheduleReq.getTimeZone();

        if (notBeforeHour > notAfterHour) {
            throw new IllegalRoomCreateArgumentException("notBeforeHour cannot be greater than notAfterHour");
        }

        Date startDate = null;
        Date endDate = null;

        if (scheduleType == ScheduleType.ONCE) {
            if (startDateString == null) {
                throw new IllegalRoomCreateArgumentException("Start date cannot be empty for ONCE room type");
            }
            if (endDateString == null) {
                throw new IllegalRoomCreateArgumentException("End date cannot be empty for ONCE room type");
            }
            startDate = Date.valueOf(startDateString);
            endDate = Date.valueOf(endDateString);
        }

        TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);


        RoomSchedule roomSchedule = RoomSchedule.builder()
                .minuteIntervals(minuteIntervals)
                .notBeforeHour(notBeforeHour)
                .notAfterHour(notAfterHour)
                .startDate(startDate)
                .endDate(endDate)
                .timeZone(timeZone)
                .scheduleType(scheduleType)
                .build();

        String link = CodeUtils.generateNewRoomCode();

        while (roomRepository.existsByLink(link)) {
            link = CodeUtils.generateNewRoomCode();
        }

        Room room = Room.builder()
                .name(name)
                .description(description)
                .roomSchedule(roomSchedule)
                .link(link)
                .owner(user)
                .moderators(moderators)
                .roomType(roomType)
                .userSchedules(new ArrayList<>())
                .build();

        room = roomRepository.save(room);

        return RoomMapper.toRoomDto(room);

    }

    public RoomDto viewRoomByLink(String roomLink) {
        User user = authService.getCurrentUserEntity();
        Optional<Room> optionalRoom = roomRepository.findByLink(roomLink);

        if (optionalRoom.isEmpty()) {
            throw new RoomNotFoundException("Room with link " + roomLink + " not found");
        }

        Room room = optionalRoom.get();

        RoomType roomType = room.getRoomType();
        if (roomType == RoomType.INVITED_ONLY) {

            if (!checkRoomInvite(room, user)) {
                throw new NotInvitedToRoomException("You are not invited to this room.");
            }
        }

        return RoomMapper.toRoomDto(room);
    }

    private boolean checkRoomInvite(Room room, User user) {
        List<RoomInvitation> roomInvitations = room.getInvitations();

        boolean isInvited = false;

        for(RoomInvitation roomInvitation : roomInvitations) {
            if (roomInvitation.getInvitee().equals(user)) {
                isInvited = true;
                switch (roomInvitation.getStatus()) {
                    case SENT:
                        throw new RoomInvitationNotAcceptedException();
                    case ACCEPTED:
                        break;
                    case DECLINED:
                        throw new RoomInvitationDeclinedException();
                    case SEEN:
                        throw new RoomInvitationNotAcceptedException();
                    default:
                        throw new RoomInvitationNotAcceptedException();
                }
            }
        }

        if (!isInvited) {
            throw new NotInvitedToRoomException("You are not invited to this room.");
        }
        return isInvited;
    }

    public RoomDto vote(String roomLink, ReqVoteUserSchedule reqVoteUserSchedule) {
        User user = authService.getCurrentUserEntity();
        Optional<Room> optionalRoom = roomRepository.findByLink(roomLink);

        if (optionalRoom.isEmpty()) {
            throw new RoomNotFoundException("Room with link " + roomLink + " not found");
        }

        Room room = optionalRoom.get();

        RoomType roomType = room.getRoomType();
        if (roomType == RoomType.INVITED_ONLY) {
            if (!checkRoomInvite(room, user)) {
                throw new NotInvitedToRoomException("You are not invited to this room.");
            }
        }

        Optional<UserSchedule> optionalUserSchedule = userScheduleRepository.findByRoomLink(roomLink);

        optionalUserSchedule.ifPresent(userScheduleRepository::delete);

        Map<String, List<String>> dates = reqVoteUserSchedule.getDates();
        List<UserScheduleTimes> userScheduleTimesList = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : dates.entrySet()) {
            String date = entry.getKey();
            List<String> times = entry.getValue();

            LocalDate localDate = LocalDate.parse(date);
            List<LocalTime> localTimes = new ArrayList<>();

            for (String time : times) {
                if (time.length() != 4) {
                    throw new IllegalVoteArgumentException("Time must be in HHMM format");
                }
                int hour = Integer.parseInt(time.substring(0, 2));
                int minute = Integer.parseInt(time.substring(2, 4));

                if (hour < 0 || hour > 23) {
                    throw new IllegalVoteArgumentException("Hour must be between 0 and 23");
                }
                if (minute < 0 || minute > 59) {
                    throw new IllegalVoteArgumentException("Minute must be between 0 and 59");
                }

                LocalTime localTime = LocalTime.parse(time);
                localTimes.add(localTime);
            }

            UserScheduleTimes userScheduleTimes = UserScheduleTimes.builder()
                    .date(localDate)
                    .times(localTimes)
                    .build();
            userScheduleTimes = userScheduleTimesRepository.save(userScheduleTimes);
            userScheduleTimesList.add(userScheduleTimes);
        }

        String timeZoneString = reqVoteUserSchedule.getTimeZone();

        TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);

        UserSchedule userSchedule = UserSchedule.builder()
                .owner(user)
                .dates(userScheduleTimesList)
                .timeZone(timeZone)
                .build();

        userSchedule = userScheduleRepository.save(userSchedule);

        // TODO: Convert the timezones of the dates to UTC

        List<UserSchedule> userSchedules = room.getUserSchedules();
        userSchedules.add(userSchedule);
        room.setUserSchedules(userSchedules);

        room = roomRepository.save(room);

        return RoomMapper.toRoomDto(room);
    }

    public List<RoomDto> listRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(RoomMapper::toRoomDto).collect(Collectors.toList());
    }

    public List<RoomDto> listUserRooms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Room> ownedRooms = roomRepository.findAllByOwner(user);
        List<Room> moderatedRooms = roomRepository.findAllByModeratorsContaining(user);
        // Combine and remove duplicates
        Set<Room> combinedRooms = new HashSet<>();
        combinedRooms.addAll(ownedRooms);
        combinedRooms.addAll(moderatedRooms);
        return combinedRooms.stream().map(RoomMapper::toRoomDto).collect(Collectors.toList());
    }

    public RoomInvitationDto inviteUserToRoom(String roomLink, ReqInviteUserToRoom reqInviteUserToRoom) {

        User inviter = authService.getCurrentUserEntity();

        Long inviteeId = reqInviteUserToRoom.getInviteeId();

        Room room = roomRepository.findByLink(roomLink)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));
        User invitee = userRepository.findById(inviteeId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Check if the user is already invited
        boolean alreadyInvited = room.getInvitations().stream()
                .anyMatch(inv -> inv.getInvitee().equals(invitee));

        if (alreadyInvited) {
            throw new AlreadyInvitedToThisRoomException("User is already invited to this room");
        }

        RoomInvitation invitation = RoomInvitation.builder()
                .room(room)
                .invitee(invitee)
                .inviter(inviter)
                .acceptedAt(null)
                .status(InvitationStatus.SENT)
                .build();

        invitation = roomInvitationRepository.save(invitation);
        // Optionally, send an invitation notification to the user

        return RoomMapper.toRoomInvitationDto(invitation);
    }

    public RoomInvitationDto acceptRoomInvitation(Long invitationId) {
        User user = authService.getCurrentUserEntity();

        RoomInvitation invitation = roomInvitationRepository.findById(invitationId)
                .orElseThrow(RoomInvitationNotFoundException::new);

        if (!invitation.getInvitee().equals(user)) {
            throw new RoomInvitationNotFoundException();
        }

        if (invitation.getStatus() != InvitationStatus.SENT && invitation.getStatus() != InvitationStatus.SEEN) {
            throw new RoomInvitationNotAcceptedException("Room invitation is not in valid status");
        }

        invitation.setStatus(InvitationStatus.ACCEPTED);
        invitation.setAcceptedAt(new Date(System.currentTimeMillis()));

        invitation = roomInvitationRepository.save(invitation);

        Room room = invitation.getRoom();

        List<User> participants = room.getParticipants();
        participants.add(user);
        room.setParticipants(participants);

        room = roomRepository.save(room);

        return RoomMapper.toRoomInvitationDto(invitation);
    }

    public RoomInvitationDto declineRoomInvitation(Long invitationId) {
        User user = authService.getCurrentUserEntity();

        RoomInvitation invitation = roomInvitationRepository.findById(invitationId)
                .orElseThrow(RoomInvitationNotFoundException::new);

        if (!invitation.getInvitee().equals(user)) {
            throw new RoomInvitationNotFoundException();
        }

        if (invitation.getStatus() != InvitationStatus.SENT && invitation.getStatus() != InvitationStatus.SEEN) {
            throw new RoomInvitationNotAcceptedException("Room invitation is not in valid status");
        }

        invitation.setStatus(InvitationStatus.DECLINED);
        invitation.setAcceptedAt(new Date(System.currentTimeMillis()));

        invitation = roomInvitationRepository.save(invitation);

        return RoomMapper.toRoomInvitationDto(invitation);
    }






}

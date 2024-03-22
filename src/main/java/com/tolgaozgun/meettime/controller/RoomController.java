package com.tolgaozgun.meettime.controller;

import com.tolgaozgun.meettime.dto.*;
import com.tolgaozgun.meettime.dto.request.room.ReqAcceptRoomInvitation;
import com.tolgaozgun.meettime.dto.request.room.ReqCreateRoom;
import com.tolgaozgun.meettime.dto.request.room.ReqInviteUserToRoom;
import com.tolgaozgun.meettime.dto.request.room.ReqVoteUserSchedule;
import com.tolgaozgun.meettime.response.ApiResponse;
import com.tolgaozgun.meettime.service.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="create")
    public ResponseEntity<Object> login(@Valid @RequestBody ReqCreateRoom reqCreateRoom) {

        RoomDto roomDTO = roomService.createRoom(reqCreateRoom);

        return ResponseEntity.ok(
                ApiResponse.<RoomDto>builder()
                        .operationResultData(roomDTO)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("Room created successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="view/{roomLink}")
    public ResponseEntity<Object> viewRoom(@PathVariable String roomLink) {

        RoomDto roomDTO = roomService.viewRoomByLink(roomLink);

        return ResponseEntity.ok(
                ApiResponse.<RoomDto>builder()
                        .operationResultData(roomDTO)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("Room info gathered successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="vote/{roomLink}")
    public ResponseEntity<Object> vote(@PathVariable String roomLink,
                                       @RequestBody ReqVoteUserSchedule reqVoteUserSchedule) {

        RoomDto roomDTO = roomService.vote(roomLink, reqVoteUserSchedule);

        return ResponseEntity.ok(
                ApiResponse.<RoomDto>builder()
                        .operationResultData(roomDTO)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("Schedule sent successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="invite/{roomLink}")
    public ResponseEntity<Object> inviteUserToRoom(@PathVariable String roomLink,
                                       @RequestBody ReqInviteUserToRoom reqInviteUserToRoom) {

        RoomInvitationDto roomInvitationDto = roomService.inviteUserToRoom(roomLink, reqInviteUserToRoom);

        return ResponseEntity.ok(
                ApiResponse.<RoomInvitationDto>builder()
                        .operationResultData(roomInvitationDto)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("User invited successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="invitation/accept/{invitationId}")
    public ResponseEntity<Object> acceptRoomInvitation(@PathVariable Long invitationId) {

        RoomInvitationDto roomInvitationDto = roomService.acceptRoomInvitation(invitationId);

        return ResponseEntity.ok(
                ApiResponse.<RoomInvitationDto>builder()
                        .operationResultData(roomInvitationDto)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("Invitation accepted successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="invitation/decline/{invitationId}")
    public ResponseEntity<Object> declineRoomInvitation(@PathVariable Long invitationId) {

        RoomInvitationDto roomInvitationDto = roomService.declineRoomInvitation(invitationId);

        return ResponseEntity.ok(
                ApiResponse.<RoomInvitationDto>builder()
                        .operationResultData(roomInvitationDto)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("Invitation rejected successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }

    // List rooms
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="list")
    public ResponseEntity<Object> listRooms() {

        List<RoomDto> roomListDto = roomService.listRooms();

        return ResponseEntity.ok(
                ApiResponse.<List<RoomDto>>builder()
                        .operationResultData(roomListDto)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("Rooms listed successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }

    // List user rooms
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="list/user/{userId}")
    public ResponseEntity<Object> listUserRooms(@PathVariable Long userId) {

        List<RoomDto> roomListDto = roomService.listUserRooms(userId);

        return ResponseEntity.ok(
                ApiResponse.<List<RoomDto>>builder()
                        .operationResultData(roomListDto)
                        .operationResult(ApiResponse.OperationResult.builder()
                                .returnMessage("Rooms listed successfully")
                                .returnCode("0")
                                .returnErrors(null)
                                .build())
                        .build());
    }


}

package com.example.reactivebooking.model.dto;

import com.example.reactivebooking.validation.markers.OnPost;
import com.example.reactivebooking.validation.markers.OnPut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.example.reactivebooking.validation.messages.ValidationMessages.MANDATORY_FIELD;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressCommand {
    @NotBlank(message = MANDATORY_FIELD, groups = OnPut.class)
    private String id;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPost.class, OnPut.class})
    private String streetAddress;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPost.class, OnPut.class})
    private String zipCode;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPost.class, OnPut.class})
    private String city;
}

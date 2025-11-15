package com.makerspace.makerspaceapp.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberID implements Serializable {
    private Long project;
    private Long user;
}
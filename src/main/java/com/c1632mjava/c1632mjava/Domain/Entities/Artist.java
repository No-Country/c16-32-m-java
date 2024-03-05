package com.c1632mjava.c1632mjava.Domain.Entities;

import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="artists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist implements Serializable {

    @Id
    private String artistId;
    private String artistName;
}

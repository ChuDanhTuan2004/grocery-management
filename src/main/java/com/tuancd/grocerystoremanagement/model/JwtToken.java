package com.tuancd.grocerystoremanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jwt_tokens")
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
    private String token;
    private boolean isValid = false;
    public JwtToken(User user, String token, boolean isValid) {
        this.user = user;
        this.token = token;
        this.isValid = isValid;
    }
}

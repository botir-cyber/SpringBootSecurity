package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.project.entity.User;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResUser {
    public ResUser(User user) {
        this.id=user.getId();
        this.fullName=user.getFullName();
        this.username=user.getUsername();
        this.phoneNumber=user.getPhoneNumber();
    }

    private UUID id;
    private String fullName;
    private String username;
    private String phoneNumber;
}

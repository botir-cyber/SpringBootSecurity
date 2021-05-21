package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqUser {
    private UUID id;
    private String fullName;
    private String userName;
    private String phoneNumber;
    private UUID universityId;
}

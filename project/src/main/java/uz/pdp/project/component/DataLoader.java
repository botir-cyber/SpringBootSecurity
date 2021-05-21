
package uz.pdp.project.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.project.entity.*;
import uz.pdp.project.entity.enums.RoleEnum;
import uz.pdp.project.repository.*;
import uz.pdp.project.security.JwtTokenProvider;

import java.util.Collection;
import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;


    @Autowired
    RegionRepository regionRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Region tashkent = regionRepository.save(new Region("Tashkent"));
            District yunusobod = districtRepository.save(new District("Mirzo Ulug'bek", tashkent));
            University university = universityRepository
                    .save(new University("Inha", "borda", "Inha", "url", yunusobod));
            Role save = roleRepository.save(new Role(RoleEnum.ADMIN));
            Role r = roleRepository.save(new Role(RoleEnum.USER));
            Role r1 = roleRepository.save(new Role(RoleEnum.MODER));
            User save1 = userRepository.save(
                    new User("Aziz", "Aziz", "1234567890113", "Faculty",
                            Collections.singletonList(save), university));
            System.out.println(jwtTokenProvider.generateToken(save1));
            User save2 = userRepository.save(
                    new User("Aziz1", "Aziz1", "1234567890112", "Faculty1",
                            Collections.singletonList(save), university));
            User save3 = userRepository.save(
                    new User("Aziz3", "Aziz3", "1234567890111", "Faculty3",
                            Collections.singletonList(save), university));
            System.out.println(university.getId().toString()+" university");
            System.out.println(tashkent.getId().toString()+" region");
            System.out.println(yunusobod.getId().toString()+" district");
            //universityRepository.createQueryForHexToDecimal();

        }
    }
}

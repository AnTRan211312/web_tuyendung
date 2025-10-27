package com.TranAn.BackEnd_Works.init;


import com.TranAn.BackEnd_Works.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataValidator implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        checkRoleTable();
    }

    private void checkRoleTable() {
        if (
                !roleRepository.existsByName("ADMIN")
                        || !roleRepository.existsByName("USER")
                        || !roleRepository.existsByName("RECRUITER")
        )
            throw new IllegalStateException("Dữ liệu bảng Role không hợp lệ");
    }
}


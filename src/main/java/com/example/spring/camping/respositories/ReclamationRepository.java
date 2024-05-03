package com.example.spring.camping.respositories;

import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
}

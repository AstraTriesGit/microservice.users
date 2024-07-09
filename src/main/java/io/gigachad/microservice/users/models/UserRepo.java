package io.gigachad.microservice.users.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<MSUser, Integer> {

}

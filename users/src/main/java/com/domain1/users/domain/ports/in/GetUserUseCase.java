package com.domain1.users.domain.ports.in;

import com.domain1.users.domain.model.User;
import reactor.core.publisher.Mono;

public interface GetUserUseCase {
 Mono <User> getById(Long id);

}

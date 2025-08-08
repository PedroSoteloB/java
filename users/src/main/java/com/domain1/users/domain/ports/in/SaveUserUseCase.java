package com.domain1.users.domain.ports.in;
import reactor.core.publisher.Mono;

import com.domain1.users.domain.model.User;

public interface SaveUserUseCase {
   Mono  <Void> save (User user);

}

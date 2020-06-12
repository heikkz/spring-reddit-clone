package com.example.springredditclone.service;

import com.example.springredditclone.dto.AuthenticationResponse;
import com.example.springredditclone.dto.LoginRequest;
import com.example.springredditclone.dto.RegisterRequest;
import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.model.NotificationEmail;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.VerificationToken;
import com.example.springredditclone.repository.UserRepository;
import com.example.springredditclone.repository.VerificationTokenRepository;
import com.example.springredditclone.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.example.springredditclone.util.Constants.ACTIVATION_EMAIL;

/**
 * Сервис регистрации/аутентификации пользователя
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final VerificationTokenRepository verificationTokenRepository;

    /**
     * Регистрация пользователя
     * @param request пользовательские данные
     */
    @Transactional
    public void signup(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodePassword(request.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token);
        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
    }

    /**
     * Сгененировать токен подтверждения, для активации пользователя
     * @param user пользователь
     * @return токен подтверждения
     */
    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    /**
     * Закодировать пароль
     * @param password пароль
     * @return закодированный пароль
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Логин пользователя
     * @param loginRequest запрос
     * @return ответ на аутентификацию
     */
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

    /**
     * Верифицировать пользователя, после регистрации, по токену
     * @param token токен верификации
     */
    public void verifyAccount(String token) {
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(token);
        optionalVerificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(optionalVerificationToken.get());
    }

    /**
     * Обновить и активировать пользователя
     * @param verificationToken токен верификации
     */
    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken) {
        @NotBlank(message = "Username is required") String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User Not Found with id: " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}

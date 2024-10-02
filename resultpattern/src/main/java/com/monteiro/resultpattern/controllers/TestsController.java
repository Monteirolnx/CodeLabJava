package com.monteiro.resultpattern.controllers;

import com.monteiro.resultpattern.exceptions.TestException;
import com.monteiro.resultpattern.models.Account;
import com.monteiro.resultpattern.models.AppError;
import com.monteiro.resultpattern.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tests")
public class TestsController {

    private final Logger logger = LoggerFactory.getLogger(TestsController.class);

    @PostMapping("/authenticate-success")
    public CompletableFuture<ResponseEntity<Object>> authenticateSuccess() {
        logger.info("AuthenticateSuccess endpoint was called.");

        return simulateAccountService(true).thenApply(accountResult ->
                accountResult.map(
                        ResponseEntity::ok,
                        error -> ResponseEntity.badRequest().body(error)
                )
        );
    }

    @PostMapping("/authenticate-failure")
    public CompletableFuture<ResponseEntity<Object>> authenticateFailure() {
        logger.info("AuthenticateFailure endpoint was called.");

        return simulateAccountService(false).thenApply(accountResult ->
                accountResult.map(
                        ResponseEntity::ok,
                        error -> ResponseEntity.badRequest().body(error)
                )
        );
    }

    @GetMapping("/test-exception")
    public ResponseEntity<Object> testException() {
        throw new TestException("Test exception");
    }

    private CompletableFuture<Result<Account>> simulateAccountService(boolean isSuccess) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (isSuccess) {
                Account account = new Account("123", "John Doe");
                return Result.success(account);
            } else {
                AppError error = new AppError("Invalid PIN", "InvalidPIN");
                return Result.failure(error);
            }
        });
    }
}

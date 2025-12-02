package pe.edu.utp.bodega_rb_api.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.JwtException;
import pe.edu.utp.bodega_rb_api.dto.ErrorResponse;
import pe.edu.utp.bodega_rb_api.exception.ResourceNotFoundException;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(IOException.class)
  public ResponseEntity<ErrorResponse> hanldeIOException(IOException ex) {
    return ResponseEntity.internalServerError()
        .body(new ErrorResponse("File error", "Error al subir el archivo", HttpStatus.INTERNAL_SERVER_ERROR.value()));
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse("Invalid credentials", "Usuario y/o contraseña incorrectos",
            HttpStatus.UNAUTHORIZED.value()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponse("Denied access", "No tienes permisos para acceder a este recurso",
            HttpStatus.FORBIDDEN.value()));
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse("Invalid credentials", "usuario o contraseña incorrectos",
            HttpStatus.UNAUTHORIZED.value()));
  }

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<ErrorResponse> handleJWTException(JwtException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse("Invalid token", "Token invalido", HttpStatus.UNAUTHORIZED.value()));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("Resource not found", ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("Null pointer exception", ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleException(RuntimeException ex) {
    return ResponseEntity.internalServerError()
        .body(new ErrorResponse("Internal server error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    return ResponseEntity.internalServerError()
        .body(new ErrorResponse("Internal server error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
  }

}

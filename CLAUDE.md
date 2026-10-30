# CLAUDE.md — Smart Flash Cards (Memorize)

This file provides project-specific guidance and rules for Claude Code when working on the Smart Flash Cards project.

## Build & Test Commands
- **Build project**: `./mvnw clean compile` (or `mvnw.cmd clean compile` on Windows PowerShell)
- **Run tests**: `./mvnw test`
- **Run specific test**: `./mvnw test -Dtest=ClassName`
- **Run application**: `./mvnw spring-boot:run`

## Project Structure & Architecture
- **Language**: Java 17
- **Framework**: Spring Boot 4.1.2
- **Base Package**: `dev.roberto.memorize`
- **Goal**: Build a simple and efficient spaced repetition flashcard study application.

## Coding Style & Guidelines
- Write clean, readable Java 17 code using modern features (records, streams, optional, etc.).
- Follow standard Spring Boot best practices (dependency injection via constructors, proper annotations).
- Add unit tests for business logic (spaced repetition algorithms, deck management, card evaluation).
- Keep commit messages clear and concise.

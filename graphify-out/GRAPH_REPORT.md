# Graph Report - memorize  (2026-11-09)

## Corpus Check
- 107 files · ~25,384 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 9 file(s) not represented in the graph (top: (none) 4, .properties 3, .tmp 1)

## Summary
- 413 nodes · 653 edges · 51 communities (11 shown, 40 thin omitted)
- Extraction: 89% EXTRACTED · 11% INFERRED · 0% AMBIGUOUS · INFERRED: 74 edges (avg confidence: 0.85)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `07b6e253`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- User
- Card
- MemorizeApplicationTests.java
- org.springframework.security.core.Authentication
- Deck
- Changelog
- SecurityConfig.java
- Supabase
- Changelog
- Writing Guidelines for Postgres References
- Section Definitions
- Supabase Postgres Best Practices
- CLAUDE.md — Smart Flash Cards (Memorize)
- flashcards-dev.md
- MemorizeApplication.java
- advanced-full-text-search.md
- advanced-jsonb-indexing.md
- conn-idle-timeout.md
- conn-limits.md
- conn-pooling.md
- conn-prepared-statements.md
- data-batch-inserts.md
- data-n-plus-one.md
- data-pagination.md
- data-upsert.md
- lock-advisory.md
- lock-deadlock-prevention.md
- lock-short-transactions.md
- lock-skip-locked.md
- monitor-explain-analyze.md
- monitor-pg-stat-statements.md
- monitor-vacuum-analyze.md
- query-composite-indexes.md
- query-covering-indexes.md
- query-index-types.md
- query-missing-indexes.md
- query-partial-indexes.md
- schema-constraints.md
- schema-data-types.md
- schema-foreign-key-indexes.md
- schema-lowercase-identifiers.md
- schema-partitioning.md
- schema-primary-keys.md
- security-privileges.md
- security-rls-basics.md
- security-rls-performance.md
- _template.md
- .mcp.json
- dev.roberto:memorize

## God Nodes (most connected - your core abstractions)
1. `Deck` - 31 edges
2. `Card` - 30 edges
3. `User` - 30 edges
4. `FlashcardService` - 23 edges
5. `UserRepository` - 18 edges
6. `UserResponse` - 15 edges
7. `JwtTokenProvider` - 15 edges
8. `MemorizeApplicationTests` - 14 edges
9. `CardController` - 12 edges
10. `DeckController` - 12 edges

## Surprising Connections (you probably didn't know these)
- `CardController` --references--> `FlashcardService`  [EXTRACTED]
  src/main/java/dev/roberto/memorize/controller/CardController.java → src/main/java/dev/roberto/memorize/service/FlashcardService.java
- `DeckController` --references--> `FlashcardService`  [EXTRACTED]
  src/main/java/dev/roberto/memorize/controller/DeckController.java → src/main/java/dev/roberto/memorize/service/FlashcardService.java
- `DeckRepository` --references--> `Deck`  [EXTRACTED]
  src/main/java/dev/roberto/memorize/repository/DeckRepository.java → src/main/java/dev/roberto/memorize/model/Deck.java
- `JwtAuthenticationFilter` --references--> `UserRepository`  [EXTRACTED]
  src/main/java/dev/roberto/memorize/security/JwtAuthenticationFilter.java → src/main/java/dev/roberto/memorize/repository/UserRepository.java
- `JwtAuthenticationFilter` --references--> `JwtTokenProvider`  [EXTRACTED]
  src/main/java/dev/roberto/memorize/security/JwtAuthenticationFilter.java → src/main/java/dev/roberto/memorize/security/JwtTokenProvider.java

## Import Cycles
- None detected.

## Communities (51 total, 40 thin omitted)

### Community 0 - "User"
Cohesion: 0.08
Nodes (4): ErrorResponse, LoginRequest, LoginResponse, UserResponse

### Community 1 - "Card"
Cohesion: 0.08
Nodes (4): Card, CardRepository, DeckRepository, FlashcardService

### Community 2 - "MemorizeApplicationTests.java"
Cohesion: 0.06
Nodes (6): DataInitializer, AuthController, User, UserRepository, JwtTokenProvider, MemorizeApplicationTests

### Community 5 - "Changelog"
Cohesion: 0.10
Nodes (20): [0.1.3](https://github.com/supabase/agent-skills/compare/v0.1.2...v0.1.3) (2026-06-02), [0.1.4](https://github.com/supabase/agent-skills/compare/v0.1.3...v0.1.4) (2026-06-05), [0.1.5](https://github.com/supabase/agent-skills/compare/v0.1.4...v0.1.5) (2026-07-10), [0.1.6](https://github.com/supabase/agent-skills/compare/v0.1.5...supabase-v0.1.6) (2026-07-30), [0.1.7](https://github.com/supabase/agent-skills/compare/v0.1.6...supabase-v0.1.7) (2026-08-12), [0.1.8](https://github.com/supabase/agent-skills/compare/v0.1.7...supabase-v0.1.8) (2026-09-24), [0.1.9](https://github.com/supabase/agent-skills/compare/v0.1.8...supabase-v0.1.9) (2026-09-28), Bug Fixes (+12 more)

### Community 7 - "Supabase"
Cohesion: 0.11
Nodes (15): Fix suggestion, Source, What happened, Skill Feedback, Steps, Core Principles, Debugging, Making and Committing Schema Changes (+7 more)

### Community 9 - "Changelog"
Cohesion: 0.12
Nodes (16): [1.2.0](https://github.com/supabase/agent-skills/compare/v1.1.1...v1.2.0) (2026-06-02), [1.3.0](https://github.com/supabase/agent-skills/compare/v1.2.0...v1.3.0) (2026-06-05), [1.4.0](https://github.com/supabase/agent-skills/compare/v1.3.0...v1.4.0) (2026-07-10), [1.5.0](https://github.com/supabase/agent-skills/compare/supabase-postgres-best-practices-v1.4.0...supabase-postgres-best-practices-v1.5.0) (2026-07-30), [1.6.0](https://github.com/supabase/agent-skills/compare/supabase-postgres-best-practices-v1.5.0...supabase-postgres-best-practices-v1.6.0) (2026-07-30), Bug Fixes, Bug Fixes, Bug Fixes (+8 more)

### Community 10 - "Writing Guidelines for Postgres References"
Cohesion: 0.12
Nodes (15): 1. Concrete Transformation Patterns, 2. Error-First Structure, 3. Quantified Impact, 4. Self-Contained Examples, 5. Semantic Naming, Code Example Standards, Comments, Impact Level Guidelines (+7 more)

### Community 11 - "Section Definitions"
Cohesion: 0.20
Nodes (9): 1. Query Performance (query), 2. Connection Management (conn), 3. Security & RLS (security), 4. Schema Design (schema), 5. Concurrency & Locking (lock), 6. Data Access Patterns (data), 7. Monitoring & Diagnostics (monitor), 8. Advanced Features (advanced) (+1 more)

### Community 13 - "Supabase Postgres Best Practices"
Cohesion: 0.33
Nodes (5): How to Use, References, Rule Categories by Priority, Supabase Postgres Best Practices, When to Apply

### Community 14 - "CLAUDE.md — Smart Flash Cards (Memorize)"
Cohesion: 0.40
Nodes (4): Build & Test Commands, CLAUDE.md — Smart Flash Cards (Memorize), Coding Style & Guidelines, Project Structure & Architecture

### Community 15 - "flashcards-dev.md"
Cohesion: 0.40
Nodes (4): Como trabalhar, Contexto, Nunca, Regras do domínio

## Knowledge Gaps
- **1 isolated node(s):** `dev.roberto:memorize`
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 225 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **40 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `User` connect `MemorizeApplicationTests.java` to `User`, `Card`, `org.springframework.security.core.Authentication`, `SecurityConfig.java`?**
  _High betweenness centrality (0.059) - this node is a cross-community bridge._
- **What connects `dev.roberto:memorize` to the rest of the system?**
  _1 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `User` be split into smaller, more focused modules?**
  _Cohesion score 0.07765151515151515 - nodes in this community are weakly interconnected._
- **Why does `Card` connect `Card` to `MemorizeApplicationTests.java`, `org.springframework.security.core.Authentication`, `Deck`?**
  _High betweenness centrality (0.043) - this node is a cross-community bridge._
- **Should `Card` be split into smaller, more focused modules?**
  _Cohesion score 0.08295625942684766 - nodes in this community are weakly interconnected._
- **Why does `Deck` connect `Deck` to `Card`, `MemorizeApplicationTests.java`, `org.springframework.security.core.Authentication`?**
  _High betweenness centrality (0.042) - this node is a cross-community bridge._
- **Should `MemorizeApplicationTests.java` be split into smaller, more focused modules?**
  _Cohesion score 0.06168831168831169 - nodes in this community are weakly interconnected._
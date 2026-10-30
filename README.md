Como usar o Docker para rodar o backend conectado ao Supabase:

1. Construir a imagem Docker:
   No terminal, na pasta raiz do projeto, execute:
   docker build -t memorize-backend .
2. Rodar o container passando as variáveis de ambiente do seu banco de dados Supabase:
   Substitua sua_url_do_supabase, seu_usuario e sua_senha pelos dados reais do seu projeto no Supabase:
   docker run -d -p 8080:8080 `
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://sua_url_do_supabase:5432/postgres `
   -e SPRING_DATASOURCE_USERNAME=seu_usuario `
  -e SPRING_DATASOURCE_PASSWORD=sua_senha `
   memorize-backend
   (No Linux/macOS, use \ em vez de ` para quebra de linha).

Onde hospedar gratuitamente ou com baixo custo:

Com este Dockerfile, você pode fazer deploy do seu backend facilmente em plataformas como:
- Render (suporta Web Services via Docker)
- Railway (detecta o Dockerfile automaticamente)
- Fly.io (ótimo suporte a Docker)
- Koyeb
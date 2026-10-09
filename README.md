# project-plancton

API de previsao de qualidade de conexao de internet desenvolvida para a atividade de APS II.

## 1. Proposito do Projeto

O objetivo desta aplicacao e fornecer previsoes de desempenho e qualidade de conexao de internet com base em metricas historicas de sondas (probes) e modelos de predicao cadastrados. A API permite consultar previsoes temporais, verificar a viabilidade de atividades online (ex.: streaming, jogos, videoconferencias) e listar os modelos preditivos disponiveis.

## 2. Requisitos

Para compilar e executar o projeto localmente, sao necessarios:

- Java JDK 17 ou superior
- Apache Maven 3.8 ou superior (ou suporte a Maven integrado em sua IDE)
- Git para controle de versao

## 3. Instrucoes de Execucao

A aplicacao foi desenvolvida com Spring Boot 3.4.0 e utiliza a porta padrao 8080.

### 3.1 Execucao via Terminal (Maven)

Na pasta raiz do projeto, execute o comando:

```bash
mvn spring-boot:run
```

Ou, caso prefira gerar o pacote executavel JAR:

```bash
mvn clean package
java -jar target/connectivity-forecast-api-1.0.0.jar
```

### 3.2 Execucao via IDE (IntelliJ, Eclipse ou VS Code)

1. Importe o projeto como projeto Maven existente.
2. Aguarde o download das dependencias.
3. Execute a classe principal:
   `com.brazcubas.apsii.ConnectivityForecastApiApplication`

Apos a inicializacao, a API estara acessivel em: `http://localhost:8080`

## 4. Documentacao da API (OpenAPI e Swagger UI)

A API possui documentacao interativa gerada automaticamente pelo SpringDoc OpenAPI:

- Interface Swagger UI: `http://localhost:8080/swagger-ui.html` (ou acessando a raiz `http://localhost:8080/`)
- Contrato OpenAPI em JSON: `http://localhost:8080/v3/api-docs`

## 5. Como Testar as APIs de Modelos Preditivos

Conforme abordado nas instrucoes de aula e no exercicio de documentacao OpenAPI, o catalogo de modelos oferece dois endpoints principais: `getModel index` (listar modelos) e `getModel` (obter modelo por ID).

### 5.1 Teste da API getModel index (Listar Modelos)

Retorna a lista de todos os modelos preditivos que estao ativos no sistema.

- Metodo: GET
- Rota: `/api/v1/models`
- URL completa: `http://localhost:8080/api/v1/models`

Exemplo de teste via terminal (curl):

```bash
curl -X GET http://localhost:8080/api/v1/models
```

Exemplo de resposta esperada (200 OK):

```json
{
  "items": [
    {
      "id": "model-a",
      "name": "Modelo A",
      "description": "Modelo de previsao desenvolvido pelo Grupo 1.",
      "groupName": "Grupo 1",
      "algorithm": "Linear Regression",
      "version": "1.0"
    },
    {
      "id": "model-b",
      "name": "Modelo B",
      "description": "Modelo de previsao desenvolvido pelo Grupo 2.",
      "groupName": "Grupo 2",
      "algorithm": "Random Forest",
      "version": "1.0"
    }
  ],
  "total": 4
}
```

### 5.2 Teste da API getModel (Obter Modelo por ID)

Retorna os detalhes completos de um modelo especifico a partir do seu identificador (`model_id`).

- Metodo: GET
- Rota: `/api/v1/models/{model_id}`
- Parametro de caminho: `model_id` (exemplo: `model-a`)
- URL completa: `http://localhost:8080/api/v1/models/model-a`

Exemplo de teste via terminal (curl):

```bash
curl -X GET http://localhost:8080/api/v1/models/model-a
```

Exemplo de resposta esperada de sucesso (200 OK):

```json
{
  "id": "model-a",
  "name": "Modelo A",
  "description": "Modelo de previsao desenvolvido pelo Grupo 1.",
  "groupName": "Grupo 1",
  "algorithm": "Linear Regression",
  "version": "1.0",
  "active": true
}
```

Exemplo de teste com modelo inexistente (retorno 404 Not Found):

```bash
curl -X GET http://localhost:8080/api/v1/models/modelo-inexistente
```

Resposta de erro esperada (404 Not Found):

```json
{
  "code": "MODEL_NOT_FOUND",
  "message": "The requested prediction model was not found."
}
```

## 6. Limitacoes do Projeto

Importante: O leitor e avaliador deste projeto nao deve interpretar os dados e rotas como um servico de inteligencia artificial ou predicao em tempo real em producao.

- Dados estaticos de exemplo: Os modelos preditivos e os historicos de predicoes sao carregados a partir de arquivos locais estaticos (`data/models.json` e `data/predictions.csv`).
- Sem persistencia em banco de dados: Nao ha integracao com banco de dados relacional ou NoSQL nesta versao; qualquer alteracao nos arquivos em tempo de execucao requer reinicializacao da aplicacao.
- Sem inferencia de Machine Learning em tempo real: As previsoes retornadas correspondem a registros pre-calculados fornecidos na base estatica, nao havendo treinamento continuo ou geracao de inferencia dinamica no backend.

## 7. Identificacao da Squad

- Squad: Squad 19 (Plancton)
- Repositorio: https://github.com/AlreadyDone675/project-plancton.git


# 🧪 Projeto de Automação Web com Selenium + Cucumber

Este repositório contém um projeto de **testes automatizados em interface gráfica**, desenvolvido com **Selenium WebDriver** e **Cucumber**, utilizando boas práticas de estruturação e reutilização de código para manter os testes limpos, eficientes e fáceis de manter.

---

## 🌐 Sobre o projeto

Nosso objetivo é validar o comportamento da aplicação web de forma automatizada, simulando interações reais do usuário com o navegador. A automação cobre cenários críticos, garantindo confiabilidade e qualidade da aplicação.

---

## ⚙️ Arquitetura do projeto

O projeto é construído com foco em reutilização de código e clareza nos testes. Para isso, conta com:

- 🧩 **Classe utilitária com métodos interativos**  
  Criamos uma classe dedicada repleta de **métodos genéricos e reutilizáveis**, como:
  - clicar em elementos
  - preencher campos
  - aguardar elementos visíveis
  - tirar screenshots
  - rolar a página
  - manipular dropdowns, modais e outros elementos do DOM

Essa abordagem permite que os passos dos testes sejam escritos de forma clara, evitando duplicação e facilitando manutenção e evolução dos testes.

---

## 🛠️ Tecnologias utilizadas

- **Java 17+**
- **Selenium WebDriver**
- **Cucumber + Gherkin (BDD)**
- **JUnit**
- **Maven**
- **WebDriverManager**
- **Lombok** (para simplificar código)
- **Log4j** (logs dos testes)
- **Allure** (opcional para relatórios)

---

## 📁 Estrutura do projeto
src └── test ├── java │ ├── steps # Definição dos passos em Gherkin │ ├── support # Classe base com métodos interativos │ ├── runners # Runner dos testes │ └── pages # Page Objects (se aplicável) └── resources └── features # Arquivos .feature (BDD)


---

## 🚀 Como executar

### Pré-requisitos

- Java 17+
- Maven
- Naveador (Chrome ou Firefox)
- IDE (IntelliJ, Eclipse, VSCode...)

### Executando os testes

```bash
mvn test
```

## 📖 Exemplo de cenário .feature

```gherkin

Feature: Testar fluxo de login

  Scenario: Login com usuário válido
    Given que estou na página de login
    When preencho o usuário "usuario123"
    And preencho a senha "senha123"
    And clico no botão de login
    Then devo ver a mensagem "Bem-vindo"
```

## 🎯 Benefícios dessa automação
✨ Métodos genéricos reutilizáveis para maior produtividade

🧼 Código limpo e fácil de manter

🧪 Testes que refletem a experiência real do usuário

👨‍💻 Estrutura pensada para escalar com o crescimento do projeto

## 🤝 Contribuições
Sugestões, melhorias e contribuições são super bem-vindas!
Sinta-se à vontade para abrir issues ou mandar um pull request 🚀

Com carinho,
Mafe 💻🧼✨


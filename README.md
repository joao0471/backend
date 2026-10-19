# Sistema de Monitoramento de Vegetação em Rodovias

## 👨‍💻 Integrantes
* **Arthur Nunes Sandes** - 562556
* **Davi Fontes Silva** - 561697
* **Giovanna Carmona Roman** Lopes - 563469
* **Ian Batinga Barbosa** - 561435
* **João Vitor Custodio da Silveira** - 562126 
* **Pedro Araújo** - 563583

## 🔗 Links Oficiais
* **Repositório Backend:** https://github.com/joao0471/backend
* **Repositório Frontend:** https://github.com/joao0471/mobile
* **Vídeo de Demonstração (YouTube):** [COLE_O_LINK_DO_YOUTUBE_AQUI]

---

## 📌 O que o sistema monitora
Este projeto consiste em um sistema de monitoramento ambiental focado na vegetação no entorno de rodovias. Através de uma API e um aplicativo móvel integrados, o sistema coleta, processa e exibe dados de sensores físicos (como umidade do solo, temperatura e crescimento). O objetivo é avaliar a saúde da vegetação em tempo real para prevenção de acidentes e preservação ambiental.

## 🛠️ Stack Tecnológica
* **Backend:** Spring Boot (Java) + H2 Database (Persistência)
* **Frontend:** React Native + Expo + TypeScript + Axios

## 📂 Estrutura do Projeto Mobile (`src/`)
A organização do código mobile segue o padrão de separação de responsabilidades:
* `types/`: Define as interfaces (tipos) consumidas pela aplicação para garantir consistência com a API.
* `services/`: Contém o arquivo `api.ts` (configurado com Axios) responsável por isolar e realizar todas as chamadas HTTP.
* `interfaces/` (screens): Contém o componente de interface `PainelMonitoramento.tsx` responsável por gerenciar a exibição dos estados e capturar as interações do usuário.

## 🧩 Modelagem de Dados (Tipos)
As tipagens no React Native foram perfeitamente alinhadas com o JSON retornado pela API Spring Boot.

```typescript
export type Sensor = {
  id: number;
  nome: string;
  tipo: string;
  unidade: string;
  limiteMinimo: number;
  limiteMaximo: number;
  ativo: boolean;
};

export type Medicao = {
  id: number;
  sensorId: number;
  sensorNome: string;
  valor: number;
  data: string; 
  status: 'NORMAL' | 'ALERTA' | 'CRITICO'; 
};

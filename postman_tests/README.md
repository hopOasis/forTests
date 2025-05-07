# 📦 Postman CLI Tests

Ця директорія містить документацію до автотестів, які запускаються за допомогою **Postman CLI** у GitHub Actions.

## 🚀 Що тестується

Тестуєтьс система використовуючи колекцію, збережену в Postman.

## 🛠 Технічні деталі

- **Інструмент**: [Postman CLI](https://www.postman.com/downloads/cli/)
- **CI/CD**: [GitHub Actions](https://github.com/features/actions)
- **Тип запуску**: Автоматичний при кожному `push` або `pull request` у гілку `main`.

## 🔧 Налаштування

### 1. Колекція

- **ID**: `тут свій`
- Редагується в UI Postman. Щоб оновити, зайди у вкладку **Collections → ⋮ → View details**.

### 2. Середовище

- **Environment ID**: `🔒 Вкажи тут свій`
- Для запуску необхідно визначити змінні середовища (наприклад, токен, URL тощо).
  
### 3. API Key

- API-ключ додається в GitHub як `Secret` з назвою: `POSTMAN_API_KEY`.
- Генерується у Postman: **Settings → API keys → Generate**.

---

## ⚙️ Workflow

Файл GitHub Actions розташований у:


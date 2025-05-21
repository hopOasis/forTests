Автоматизовані API тести з Postman
Що це?
Цей репозиторій містить API тести, створені у Postman. 
Їх можна запускати:

- локально на своєму комп’ютері
- автоматично через GitHub Actions при кожному пуші у гілку master

Як запускати тести локально
Що потрібно:
-Встановити Postman CLI
- Мати свій API ключ Postman

Кроки:
Встанови Postman CLI:
curl -o- "https://dl-cli.pstmn.io/install/linux64.sh" | sh

Увійди в Postman CLI за допомогою API ключа (отримай ключ у своєму акаунті Postman):
postman login --with-api-key <твій_ключ>

Запусти тести командою:
postman collection run "<ID_колекції>" -e "<ID_середовища>"

Як працює автоматичний запуск у GitHub
Тести запускаються автоматично при пуші в гілку master
Використовується GitHub Actions — сервіс автоматизації

Що потрібно налаштувати
У репозиторії на GitHub зайди в Settings → Secrets and variables → Actions
Додай новий секрет з ім’ям POSTMAN_API_KEY і встав туди свій API ключ Postman

Як виглядає GitHub Actions (workflow)
У папці .github/workflows/ є файл run-postman-tests.yml з певним кодом

Колекції та середовища зберігаються у Postman (не в репозиторії)
API ключ захищений через секрети GitHub
Тести автоматично запускаються на сервері GitHub при кожному пуші в master


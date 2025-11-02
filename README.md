# Recipe Finder - приложение для поиска кулинарных рецептов

## 📋 Общие требования

Приложение поддерживает устройства, начиная с Android 9.0 (minSdkVersion = 28).

## 🚀 Начало работы
1. Клонируйте репозиторий с помощью команды `git clone https://github.com/marugish/RecipesApp.git`
2. Откройте проект в Android Studio
3. Подключить устройство (телефон/эмулятор)
4. Запустите приложение ▶️

⚠️ **Важная информация**  
Из соображений безопасности API-ключи внешних сервисов не включены в репозиторий. Поэтому при сборке проекта через Android Studio часть функциональности будет недоступна. Для полноценной работы приложения добавьте свои ключи:
- Firebase
- Spoonacular API

## ⚙️ Возможности приложения (Features)

| Возможности приложения | Прогресс |
| ----------- | :------: |
| Регистрация нового пользователя | ✅ |
| Авторизация по электронной почте | ✅ |
| Авторизация через Google-аккаунт | ✅ |
| Авторизация через GitHub | ⚠️ bugfix |
| Авторизация через Microsoft | ❌ |
| Сброс пароля | 🔨 |
| Домашняя страница пользователя | 🔨 |
| Поиск кулинарных рецептов | ✅ |
| Детали рецепта | ✅ |
| Персонализированные рекомендации | ✅ |
| Фильтрация по категориям | 🔨 |
| Добавление/удаление рецептов из Избранного | 🔨 |
| Подборка кулинарных фактов и шуток | 🔨 |
| Добавление/удаление фактов/шуток из Избранного | 🔨 |

## 🛠️ Используемые инструменты и технологии

| Технологии | Технологии |
| ----------- | :------: |
| Single Activity | Fragment |
| MVVM | Clean Architecture |
| ViewBinding | Material 3 |
| RecyclerView | Jetpack Navigation Component |
| Koin | Glide |
| Retrofit 2 | Gson |
| DataStore | Room |
| Kotlin Flow | Kotlin Coroutines |
| Firebase Auth | Firebase Firestore Database |

## 📱 Скриншоты приложения (Screenshots)

### 🔐 Авторизация и регистрация
<img src="https://github.com/user-attachments/assets/ffa401ab-4aa1-4868-9dce-bfb8fe833411" width="300" alt="Authorization"> <img src="https://github.com/user-attachments/assets/af8647cc-66a4-41f3-b0f2-a7e08a94e638" width="300"> <img src="https://github.com/user-attachments/assets/394a8e07-cc72-4042-b110-eee5803a8325" width="300">


### 🔍 Поиск рецептов и персонализированные рекомендации
<img src="https://github.com/user-attachments/assets/53740a31-b328-46bc-89bb-b0f41d543fdc" width="300"> <img src="https://github.com/user-attachments/assets/60d1556b-f604-4a86-9bdf-aa4ab7941f92" width="300"> <img src="https://github.com/user-attachments/assets/789a4dc0-7b5e-486d-808e-2baec4d28264" width="300"> 


### 📋 Детали рецепта
<img src="https://github.com/user-attachments/assets/b07ac548-de8e-47f0-8730-2fb66fad3fe5" width="300"> <img src="https://github.com/user-attachments/assets/c159722b-5b28-445a-93bc-4af341d4aa99" width="300">



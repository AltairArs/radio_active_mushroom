![alt logo](https://github.com/AltairArs/radio_active_mushroom/blob/master/src/main/resources/static/img/logo_128.png)
# RadioActive Mushroom
## Описание
Сайт для создания и редактирования схем баз данных, а также генерации "сырого" SQL кода для различных БД.
## Требования
- [ ] Пользователи
	- [ ] 5 ролей
		- [ ] Администратор
		> Может все
		- [ ] Маркетолог
		> Может редактировать цены, акции и т.д.
		- [ ] Редактор
		> Может редактировать Редактор БД
		- [ ] Сотрудник
		> Объединение Редактора и Маркетолога
		- [ ] Обычный пользователь
	- [x] Аутентификация
		- [x] По Email и паролю или имени и паролю
		- [x] Отказ в случае не активированного пользователя
	- [x] Регистрация в 2 этапа
		- [x] Создание нового неактивного пользователя
		- [x] Отправка на почту письма с ссылкой для активации
		- [x] Верификация пользователя
- [ ] Администрирование
	- [ ] Пользователи
		- [ ] Просмотр
		- [ ] Деактивация
	- [ ] Редактор БД
		- [ ] Просмотр поддерживаемых версий
		- [ ] Редактирование
		- [ ] Добавление
		- [ ] Удаление
- [ ] Внешний вид
	- [x] Режимы колоризации
		- [x] Полная
		- [x] Частичная
	- [x] Тема
		- [x] Светлая
		- [x] Темная
	- [x] Различные цвета
	> Реализованы: красный, голубой, зеленый, желтый, фиолетовый, оранжевый, серый (без цвета) 
- [ ] Проекты
	- [x] Создание
	- [x] Редактирование информации
	- [x] Удаление
	- [ ] Редактирование схемы
	- [ ] Конвертация схемы
	- [ ] Генерация SQL кода
	- [ ] Скачивание
	- [ ] Загрузка
	- [ ] Импорт
	- [ ] Экспорт
	- [ ] Совместное редактирование
		- [ ] Работа с запросами на доступ
			- [ ] Создание
			- [ ] Вынесение решения
		- [ ] Обработка совместного редактирования
- [ ] Дополнительное наполнение страниц
	- [ ] Статистика пользователя в профиле
	- [ ] Страница со списком поддерживаемых версий
## Стек технологий
- [ ] Фреймворки
	- [ ] Spring (Java JDK 21)
- [ ] Базы данных
	- [ ] MongoDB
	- [ ] PostgreSQL
## Развертывание
## Запуск

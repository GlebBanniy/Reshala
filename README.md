# Reshala

Обновленная версия проекта в ветке "new".

САЙТ ДЛЯ РАЗМЕЩЕНИЯ И РЕШЕНИЯ МАТЕМАТИЧЕСКИХ ЗАДАЧ

Неаутентифицированным пользователи доступен только режим read-only (доступен поиск, недоступно создание и решение задач, недоступны комментарии, лайки и рейтинги).

Аутентифицированные пользователи имеют доступ ко всему, кроме админки. В базовом варианте админка представляет собой список пользователей (как ссылок на их страницы). Администратор видит каждую страницу пользователя и каждую задачу как ее создатель (например, может отредактировать или создать от имени пользователя с его страницы новую задачу).

У каждого пользователя есть его личная страница, на которой он видит список своих задач (таблица с сортировками, возможность создать/удалить/редактировать задачу/открыть в режиме просмотра). Так же страницы содержит информацию о личных достижениях — числе решенных задач и числе созданных задач.

Каждая задача состоит из: названия, условия, "тема" (из фиксированного набора, например, "Геометрия", "Теория чисел", "Java" и прочее), тэга, до трёх вариантов правильного ответа.

На главной странице отображаются: последние добавленные задачи, задачи с самыми большими рейтингами.

под задачей отображается поле с кнопкой для проверки решения, куда можно ввести строку и отправить на проверку (в результате проверки пользователь получает Alert "Неверное решение" или "Верное решение" соответствующего цвета и, в случае верного решения, задача отмечает у пользователя как решенная - решение у данной задачи для данного пользователя может быть только одно).

Каждый пользовать может проставить "рейтинг" (от 1 до 5) задаче (не более одного от одного пользователя на задачу) — средний рейтинг отображается у задачи рядом с названием везде на сайте.


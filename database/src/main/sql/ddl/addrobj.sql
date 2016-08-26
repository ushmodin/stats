create table addrobj (
	AOGUID varchar(36) -- Глобальный уникальный идентификатор адресного объекта 
	,FORMALNAME varchar(120) -- Формализованное наименование
	,REGIONCODE varchar(2) -- Код региона
	,AUTOCODE varchar(1) -- Код автономии
	,AREACODE varchar(3) -- Код района
	,CITYCODE varchar(3) -- Код города
	,CTARCODE varchar(3) -- Код внутригородского района
	,PLACECODE varchar(3) -- Код населенного пункта
	,STREETCODE varchar(4) -- Код улицы
	,EXTRCODE varchar(4) -- Код дополнительного адресообразующего элемента
	,SEXTCODE varchar(3) -- Код подчиненного дополнительного адресообразующего элемента
	,OFFNAME varchar(120) -- Официальное наименование
	,POSTALCODE varchar(6) -- Почтовый индекс
	,IFNSFL varchar(4) -- Код ИФНС ФЛ
	,TERRIFNSFL varchar(4) -- Код территориального участка ИФНС ФЛ
	,IFNSUL varchar(4) -- Код ИФНС ЮЛ
	,TERRIFNSUL varchar(4) -- Код территориального участка ИФНС ЮЛ
	,OKATO varchar(11) -- OKATO
	,OKTMO varchar(11) -- OKTMO
	,UPDATEDATE date -- Дата  внесения записи
	,SHORTNAME varchar(10) -- Краткое наименование типа объекта
	,AOLEVEL integer -- Уровень адресного объекта 
	,PARENTGUID varchar(36) -- Идентификатор объекта родительского объекта
	,AOID varchar(36) -- Уникальный идентификатор записи. Ключевое поле.
	,PREVID varchar(36) -- Идентификатор записи связывания с предыдушей исторической записью
	,NEXTID varchar(36) -- Идентификатор записи  связывания с последующей исторической записью
	,CODE varchar(17) -- Код адресного объекта одной строкой с признаком актуальности из КЛАДР 4.0. 
	,PLAINCODE varchar(15) -- Код адресного объекта из КЛАДР 4.0 одной строкой без признака актуальности (последних двух цифр)
	,ACTSTATUS integer -- Статус исторической записи в жизненном цикле адресного объекта:
 -- 0 – не последняя
 -- 1 - последняя
	,CENTSTATUS integer -- Статус центра
	,OPERSTATUS integer -- Статус действия над записью – причина появления записи (см. описание таблицы OperationStatus):
 -- 01 – Инициация;
 -- 10 – Добавление;
 -- 20 – Изменение;
 -- 21 – Групповое изменение;
 -- 30 – Удаление;
 -- 31 - Удаление вследствие удаления вышестоящего объекта;
 -- 40 – Присоединение адресного объекта (слияние);
 -- 41 – Переподчинение вследствие слияния вышестоящего объекта;
 -- 42 - Прекращение существования вследствие присоединения к другому адресному объекту;
 -- 43 - Создание нового адресного объекта в результате слияния адресных объектов;
 -- 50 – Переподчинение;
 -- 51 – Переподчинение вследствие переподчинения вышестоящего объекта;
 -- 60 – Прекращение существования вследствие дробления;
 -- 61 – Создание нового адресного объекта в результате дробления
 -- 
	,CURRSTATUS integer -- Статус актуальности КЛАДР 4 (последние две цифры в коде)
	,STARTDATE date -- Начало действия записи
	,ENDDATE date -- Окончание действия записи
	,NORMDOC varchar(36) -- Внешний ключ на нормативный документ
	,LIVESTATUS smallint -- Признак действующего адресного объекта
	,CADNUM varchar(100) -- Кадастровый номер
	,DIVTYPE integer -- Тип адресации:
 -- 0 - не определено
 -- 1 - муниципальный;
 -- 2 - административно-территориальный
);

COMMENT ON COLUMN addrobj.AOGUID IS 'Глобальный уникальный идентификатор адресного объекта ';
COMMENT ON COLUMN addrobj.FORMALNAME IS 'Формализованное наименование';
COMMENT ON COLUMN addrobj.REGIONCODE IS 'Код региона';
COMMENT ON COLUMN addrobj.AUTOCODE IS 'Код автономии';
COMMENT ON COLUMN addrobj.AREACODE IS 'Код района';
COMMENT ON COLUMN addrobj.CITYCODE IS 'Код города';
COMMENT ON COLUMN addrobj.CTARCODE IS 'Код внутригородского района';
COMMENT ON COLUMN addrobj.PLACECODE IS 'Код населенного пункта';
COMMENT ON COLUMN addrobj.STREETCODE IS 'Код улицы';
COMMENT ON COLUMN addrobj.EXTRCODE IS 'Код дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.SEXTCODE IS 'Код подчиненного дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.OFFNAME IS 'Официальное наименование';
COMMENT ON COLUMN addrobj.POSTALCODE IS 'Почтовый индекс';
COMMENT ON COLUMN addrobj.IFNSFL IS 'Код ИФНС ФЛ';
COMMENT ON COLUMN addrobj.TERRIFNSFL IS 'Код территориального участка ИФНС ФЛ';
COMMENT ON COLUMN addrobj.IFNSUL IS 'Код ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.TERRIFNSUL IS 'Код территориального участка ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.OKATO IS 'OKATO';
COMMENT ON COLUMN addrobj.OKTMO IS 'OKTMO';
COMMENT ON COLUMN addrobj.UPDATEDATE IS 'Дата  внесения записи';
COMMENT ON COLUMN addrobj.SHORTNAME IS 'Краткое наименование типа объекта';
COMMENT ON COLUMN addrobj.AOLEVEL IS 'Уровень адресного объекта ';
COMMENT ON COLUMN addrobj.PARENTGUID IS 'Идентификатор объекта родительского объекта';
COMMENT ON COLUMN addrobj.AOID IS 'Уникальный идентификатор записи. Ключевое поле.';
COMMENT ON COLUMN addrobj.PREVID IS 'Идентификатор записи связывания с предыдушей исторической записью';
COMMENT ON COLUMN addrobj.NEXTID IS 'Идентификатор записи  связывания с последующей исторической записью';
COMMENT ON COLUMN addrobj.CODE IS 'Код адресного объекта одной строкой с признаком актуальности из КЛАДР 4.0. ';
COMMENT ON COLUMN addrobj.PLAINCODE IS 'Код адресного объекта из КЛАДР 4.0 одной строкой без признака актуальности (последних двух цифр)';
COMMENT ON COLUMN addrobj.ACTSTATUS IS 'Статус исторической записи в жизненном цикле адресного объекта: 0 – не последняя 1 - последняя';
COMMENT ON COLUMN addrobj.CENTSTATUS IS 'Статус центра';
COMMENT ON COLUMN addrobj.OPERSTATUS IS 'Статус действия над записью – причина появления записи (см. описание таблицы OperationStatus): 01 – Инициация; 10 – Добавление; 20 – Изменение; 21 – Групповое изменение; 30 – Удаление; 31 - Удаление вследствие удаления вышестоящего объекта; 40 – Присоединение адресного объекта (слияние); 41 – Переподчинение вследствие слияния вышестоящего объекта; 42 - Прекращение существования вследствие присоединения к другому адресному объекту; 43 - Создание нового адресного объекта в результате слияния адресных объектов; 50 – Переподчинение; 51 – Переподчинение вследствие переподчинения вышестоящего объекта; 60 – Прекращение существования вследствие дробления; 61 – Создание нового адресного объекта в результате дробления ';
COMMENT ON COLUMN addrobj.CURRSTATUS IS 'Статус актуальности КЛАДР 4 (последние две цифры в коде)';
COMMENT ON COLUMN addrobj.STARTDATE IS 'Начало действия записи';
COMMENT ON COLUMN addrobj.ENDDATE IS 'Окончание действия записи';
COMMENT ON COLUMN addrobj.NORMDOC IS 'Внешний ключ на нормативный документ';
COMMENT ON COLUMN addrobj.LIVESTATUS IS 'Признак действующего адресного объекта';
COMMENT ON COLUMN addrobj.CADNUM IS 'Кадастровый номер';
COMMENT ON COLUMN addrobj.DIVTYPE IS 'Тип адресации: 0 - не определено 1 - муниципальный; 2 - административно-территориальный';
COMMENT ON COLUMN addrobj.AOGUID IS 'Глобальный уникальный идентификатор адресного объекта ';
COMMENT ON COLUMN addrobj.FORMALNAME IS 'Формализованное наименование';
COMMENT ON COLUMN addrobj.REGIONCODE IS 'Код региона';
COMMENT ON COLUMN addrobj.AUTOCODE IS 'Код автономии';
COMMENT ON COLUMN addrobj.AREACODE IS 'Код района';
COMMENT ON COLUMN addrobj.CITYCODE IS 'Код города';
COMMENT ON COLUMN addrobj.CTARCODE IS 'Код внутригородского района';
COMMENT ON COLUMN addrobj.PLACECODE IS 'Код населенного пункта';
COMMENT ON COLUMN addrobj.STREETCODE IS 'Код улицы';
COMMENT ON COLUMN addrobj.EXTRCODE IS 'Код дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.SEXTCODE IS 'Код подчиненного дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.OFFNAME IS 'Официальное наименование';
COMMENT ON COLUMN addrobj.POSTALCODE IS 'Почтовый индекс';
COMMENT ON COLUMN addrobj.IFNSFL IS 'Код ИФНС ФЛ';
COMMENT ON COLUMN addrobj.TERRIFNSFL IS 'Код территориального участка ИФНС ФЛ';
COMMENT ON COLUMN addrobj.IFNSUL IS 'Код ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.TERRIFNSUL IS 'Код территориального участка ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.OKATO IS 'OKATO';
COMMENT ON COLUMN addrobj.OKTMO IS 'OKTMO';
COMMENT ON COLUMN addrobj.UPDATEDATE IS 'Дата  внесения записи';
COMMENT ON COLUMN addrobj.SHORTNAME IS 'Краткое наименование типа объекта';
COMMENT ON COLUMN addrobj.AOLEVEL IS 'Уровень адресного объекта ';
COMMENT ON COLUMN addrobj.PARENTGUID IS 'Идентификатор объекта родительского объекта';
COMMENT ON COLUMN addrobj.AOID IS 'Уникальный идентификатор записи. Ключевое поле.';
COMMENT ON COLUMN addrobj.PREVID IS 'Идентификатор записи связывания с предыдушей исторической записью';
COMMENT ON COLUMN addrobj.NEXTID IS 'Идентификатор записи  связывания с последующей исторической записью';
COMMENT ON COLUMN addrobj.CODE IS 'Код адресного объекта одной строкой с признаком актуальности из КЛАДР 4.0. ';
COMMENT ON COLUMN addrobj.PLAINCODE IS 'Код адресного объекта из КЛАДР 4.0 одной строкой без признака актуальности (последних двух цифр)';
COMMENT ON COLUMN addrobj.ACTSTATUS IS 'Статус исторической записи в жизненном цикле адресного объекта: 0 – не последняя 1 - последняя';
COMMENT ON COLUMN addrobj.CENTSTATUS IS 'Статус центра';
COMMENT ON COLUMN addrobj.OPERSTATUS IS 'Статус действия над записью – причина появления записи (см. описание таблицы OperationStatus): 01 – Инициация; 10 – Добавление; 20 – Изменение; 21 – Групповое изменение; 30 – Удаление; 31 - Удаление вследствие удаления вышестоящего объекта; 40 – Присоединение адресного объекта (слияние); 41 – Переподчинение вследствие слияния вышестоящего объекта; 42 - Прекращение существования вследствие присоединения к другому адресному объекту; 43 - Создание нового адресного объекта в результате слияния адресных объектов; 50 – Переподчинение; 51 – Переподчинение вследствие переподчинения вышестоящего объекта; 60 – Прекращение существования вследствие дробления; 61 – Создание нового адресного объекта в результате дробления ';
COMMENT ON COLUMN addrobj.CURRSTATUS IS 'Статус актуальности КЛАДР 4 (последние две цифры в коде)';
COMMENT ON COLUMN addrobj.STARTDATE IS 'Начало действия записи';
COMMENT ON COLUMN addrobj.ENDDATE IS 'Окончание действия записи';
COMMENT ON COLUMN addrobj.NORMDOC IS 'Внешний ключ на нормативный документ';
COMMENT ON COLUMN addrobj.LIVESTATUS IS 'Признак действующего адресного объекта';
COMMENT ON COLUMN addrobj.CADNUM IS 'Кадастровый номер';
COMMENT ON COLUMN addrobj.DIVTYPE IS 'Тип адресации: 0 - не определено 1 - муниципальный; 2 - административно-территориальный';
COMMENT ON COLUMN addrobj.AOGUID IS 'Глобальный уникальный идентификатор адресного объекта ';
COMMENT ON COLUMN addrobj.FORMALNAME IS 'Формализованное наименование';
COMMENT ON COLUMN addrobj.REGIONCODE IS 'Код региона';
COMMENT ON COLUMN addrobj.AUTOCODE IS 'Код автономии';
COMMENT ON COLUMN addrobj.AREACODE IS 'Код района';
COMMENT ON COLUMN addrobj.CITYCODE IS 'Код города';
COMMENT ON COLUMN addrobj.CTARCODE IS 'Код внутригородского района';
COMMENT ON COLUMN addrobj.PLACECODE IS 'Код населенного пункта';
COMMENT ON COLUMN addrobj.STREETCODE IS 'Код улицы';
COMMENT ON COLUMN addrobj.EXTRCODE IS 'Код дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.SEXTCODE IS 'Код подчиненного дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.OFFNAME IS 'Официальное наименование';
COMMENT ON COLUMN addrobj.POSTALCODE IS 'Почтовый индекс';
COMMENT ON COLUMN addrobj.IFNSFL IS 'Код ИФНС ФЛ';
COMMENT ON COLUMN addrobj.TERRIFNSFL IS 'Код территориального участка ИФНС ФЛ';
COMMENT ON COLUMN addrobj.IFNSUL IS 'Код ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.TERRIFNSUL IS 'Код территориального участка ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.OKATO IS 'OKATO';
COMMENT ON COLUMN addrobj.OKTMO IS 'OKTMO';
COMMENT ON COLUMN addrobj.UPDATEDATE IS 'Дата  внесения записи';
COMMENT ON COLUMN addrobj.SHORTNAME IS 'Краткое наименование типа объекта';
COMMENT ON COLUMN addrobj.AOLEVEL IS 'Уровень адресного объекта ';
COMMENT ON COLUMN addrobj.PARENTGUID IS 'Идентификатор объекта родительского объекта';
COMMENT ON COLUMN addrobj.AOID IS 'Уникальный идентификатор записи. Ключевое поле.';
COMMENT ON COLUMN addrobj.PREVID IS 'Идентификатор записи связывания с предыдушей исторической записью';
COMMENT ON COLUMN addrobj.NEXTID IS 'Идентификатор записи  связывания с последующей исторической записью';
COMMENT ON COLUMN addrobj.CODE IS 'Код адресного объекта одной строкой с признаком актуальности из КЛАДР 4.0. ';
COMMENT ON COLUMN addrobj.PLAINCODE IS 'Код адресного объекта из КЛАДР 4.0 одной строкой без признака актуальности (последних двух цифр)';
COMMENT ON COLUMN addrobj.ACTSTATUS IS 'Статус исторической записи в жизненном цикле адресного объекта: 0 – не последняя 1 - последняя';
COMMENT ON COLUMN addrobj.CENTSTATUS IS 'Статус центра';
COMMENT ON COLUMN addrobj.OPERSTATUS IS 'Статус действия над записью – причина появления записи (см. описание таблицы OperationStatus): 01 – Инициация; 10 – Добавление; 20 – Изменение; 21 – Групповое изменение; 30 – Удаление; 31 - Удаление вследствие удаления вышестоящего объекта; 40 – Присоединение адресного объекта (слияние); 41 – Переподчинение вследствие слияния вышестоящего объекта; 42 - Прекращение существования вследствие присоединения к другому адресному объекту; 43 - Создание нового адресного объекта в результате слияния адресных объектов; 50 – Переподчинение; 51 – Переподчинение вследствие переподчинения вышестоящего объекта; 60 – Прекращение существования вследствие дробления; 61 – Создание нового адресного объекта в результате дробления ';
COMMENT ON COLUMN addrobj.CURRSTATUS IS 'Статус актуальности КЛАДР 4 (последние две цифры в коде)';
COMMENT ON COLUMN addrobj.STARTDATE IS 'Начало действия записи';
COMMENT ON COLUMN addrobj.ENDDATE IS 'Окончание действия записи';
COMMENT ON COLUMN addrobj.NORMDOC IS 'Внешний ключ на нормативный документ';
COMMENT ON COLUMN addrobj.LIVESTATUS IS 'Признак действующего адресного объекта';
COMMENT ON COLUMN addrobj.CADNUM IS 'Кадастровый номер';
COMMENT ON COLUMN addrobj.DIVTYPE IS 'Тип адресации: 0 - не определено 1 - муниципальный; 2 - административно-территориальный';
COMMENT ON COLUMN addrobj.AOGUID IS 'Глобальный уникальный идентификатор адресного объекта ';
COMMENT ON COLUMN addrobj.FORMALNAME IS 'Формализованное наименование';
COMMENT ON COLUMN addrobj.REGIONCODE IS 'Код региона';
COMMENT ON COLUMN addrobj.AUTOCODE IS 'Код автономии';
COMMENT ON COLUMN addrobj.AREACODE IS 'Код района';
COMMENT ON COLUMN addrobj.CITYCODE IS 'Код города';
COMMENT ON COLUMN addrobj.CTARCODE IS 'Код внутригородского района';
COMMENT ON COLUMN addrobj.PLACECODE IS 'Код населенного пункта';
COMMENT ON COLUMN addrobj.STREETCODE IS 'Код улицы';
COMMENT ON COLUMN addrobj.EXTRCODE IS 'Код дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.SEXTCODE IS 'Код подчиненного дополнительного адресообразующего элемента';
COMMENT ON COLUMN addrobj.OFFNAME IS 'Официальное наименование';
COMMENT ON COLUMN addrobj.POSTALCODE IS 'Почтовый индекс';
COMMENT ON COLUMN addrobj.IFNSFL IS 'Код ИФНС ФЛ';
COMMENT ON COLUMN addrobj.TERRIFNSFL IS 'Код территориального участка ИФНС ФЛ';
COMMENT ON COLUMN addrobj.IFNSUL IS 'Код ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.TERRIFNSUL IS 'Код территориального участка ИФНС ЮЛ';
COMMENT ON COLUMN addrobj.OKATO IS 'OKATO';
COMMENT ON COLUMN addrobj.OKTMO IS 'OKTMO';
COMMENT ON COLUMN addrobj.UPDATEDATE IS 'Дата  внесения записи';
COMMENT ON COLUMN addrobj.SHORTNAME IS 'Краткое наименование типа объекта';
COMMENT ON COLUMN addrobj.AOLEVEL IS 'Уровень адресного объекта ';
COMMENT ON COLUMN addrobj.PARENTGUID IS 'Идентификатор объекта родительского объекта';
COMMENT ON COLUMN addrobj.AOID IS 'Уникальный идентификатор записи. Ключевое поле.';
COMMENT ON COLUMN addrobj.PREVID IS 'Идентификатор записи связывания с предыдушей исторической записью';
COMMENT ON COLUMN addrobj.NEXTID IS 'Идентификатор записи  связывания с последующей исторической записью';
COMMENT ON COLUMN addrobj.CODE IS 'Код адресного объекта одной строкой с признаком актуальности из КЛАДР 4.0. ';
COMMENT ON COLUMN addrobj.PLAINCODE IS 'Код адресного объекта из КЛАДР 4.0 одной строкой без признака актуальности (последних двух цифр)';
COMMENT ON COLUMN addrobj.ACTSTATUS IS 'Статус исторической записи в жизненном цикле адресного объекта: 0 – не последняя 1 - последняя';
COMMENT ON COLUMN addrobj.CENTSTATUS IS 'Статус центра';
COMMENT ON COLUMN addrobj.OPERSTATUS IS 'Статус действия над записью – причина появления записи (см. описание таблицы OperationStatus): 01 – Инициация; 10 – Добавление; 20 – Изменение; 21 – Групповое изменение; 30 – Удаление; 31 - Удаление вследствие удаления вышестоящего объекта; 40 – Присоединение адресного объекта (слияние); 41 – Переподчинение вследствие слияния вышестоящего объекта; 42 - Прекращение существования вследствие присоединения к другому адресному объекту; 43 - Создание нового адресного объекта в результате слияния адресных объектов; 50 – Переподчинение; 51 – Переподчинение вследствие переподчинения вышестоящего объекта; 60 – Прекращение существования вследствие дробления; 61 – Создание нового адресного объекта в результате дробления ';
COMMENT ON COLUMN addrobj.CURRSTATUS IS 'Статус актуальности КЛАДР 4 (последние две цифры в коде)';
COMMENT ON COLUMN addrobj.STARTDATE IS 'Начало действия записи';
COMMENT ON COLUMN addrobj.ENDDATE IS 'Окончание действия записи';
COMMENT ON COLUMN addrobj.NORMDOC IS 'Внешний ключ на нормативный документ';
COMMENT ON COLUMN addrobj.LIVESTATUS IS 'Признак действующего адресного объекта';
COMMENT ON COLUMN addrobj.CADNUM IS 'Кадастровый номер';
COMMENT ON COLUMN addrobj.DIVTYPE IS 'Тип адресации: 0 - не определено 1 - муниципальный; 2 - административно-территориальный';

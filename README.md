# !!!All project code located in the master branch

# PetFoodStore
Java Web Development Course Final Project  

Использованы данные версии программ:
 1) Eclipse IDE for Enterprise Java Developers (includes Incubating components)  
	Version: 2020-12 (4.18.0)  
	Build id: 20201210-1552  
	-- JDK compiler compliance settings - 13  
	-- JRE - jdk-13.0.1
 2) OS: Windows 10, v.10.0, x86_64 / win32  
 3) Java version: 13.0.1  
 4) Apache Tomcat Version 9.0.41 (apache-tomcat-9.0.41)  
 5) MySQL Server version: 5.7.29-log MySQL Community Server (GPL)  

Никаких изменений с настройках Tomcat не производилось.  
Схема базы данных отправлена вместе со ссылкой на этот проект.  

Скрипт для mysql находится в папке   
/zooproject/src/main/resources/zooproject_db_script.sql  

При запуске создается база данных, пользователь используемый в дальнейшем в
(/zooproject/src/main/resources/dbconnection.properties), таблицы и пользователи приложения для тестирования.  

Пароль для всех, указанных ниже, трех пользователей: 2020epam  

janedoe@gmail.com  
ivan@gmail.com  
admin@gmail.com (администратор)  

Возможности администратора:  
Регистрация пользователя.  
Регистрация пользователя с правами администратора.  
Изменение свойств товара.  
Добавление нового товара в базу данных.  
Деактивация пользователей.  
Редактирование своего профиля.  

Ограничение Администратора:  
Нет возможности сделать заказ.  

Возможности Не Зарегистрированного Пользователя:  
Только просмотр товара.  

Возможности Зарегистрированного Пользователя:  
Добавление товара в корзину.  
Оформление заказа.  
Просмотр истории заказов.  
Редактирование своего профиля.  

# Инструкция по изображениям:  
Папка с файлами "images", находится по ссылке ниже. Архив следует разархивировать в папку D:/images/.  
База данных хранит название изображения с расширением файла.  
Путь к файлам хранится в параметрах web.xml  

Либо заменить местоположение папки на нужный путь в параметрах web.xml:  
<context-param>  
    <param-name>ImageLocation</param-name>  
    <param-value>D:/images/</param-value>  
</context-param>  

Ссылка на схему, скрипт БД, папка с картинками Backup link:  
https://drive.google.com/drive/folders/181BTxiZPtlszA38IXhX-olTqBcx-Zxvb?usp=sharing  

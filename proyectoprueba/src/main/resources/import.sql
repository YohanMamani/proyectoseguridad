USE [db_service_dia]

INSERT [dbo].[Dia] ([nombre]) VALUES ('Lunes')
INSERT [dbo].[Dia] ([nombre]) VALUES ('Martes')
INSERT [dbo].[Dia] ([nombre]) VALUES ('Miercoles')
INSERT [dbo].[Dia] ([nombre]) VALUES ('Jueves')
INSERT [dbo].[Dia] ([nombre]) VALUES ('Viernes')
INSERT [dbo].[Dia] ([nombre]) VALUES ('Sabado')
INSERT [dbo].[Dia] ([nombre]) VALUES ('Domingo') 

INSERT INTO [dbo].[Users] ([username], [password], [enabled]) VALUES ('andres','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO [dbo].[Users] ([username], [password], [enabled]) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);

INSERT INTO [dbo].[authorities] ([user_id], [authority]) VALUES (1,'ROLE_USER');
INSERT INTO [dbo].[authorities] ([user_id], [authority]) VALUES (2,'ROLE_ADMIN');
INSERT INTO [dbo].[authorities] ([user_id], [authority]) VALUES (2,'ROLE_USER');
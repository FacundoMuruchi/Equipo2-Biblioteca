# 📚 Sistema de Gestión Bibliotecaria

Sistema de gestión para bibliotecas desarrollado en Java, con interfaz de terminal interactiva y estructuras de datos eficientes.

## 🎯 Características Principales

- ✅ **Gestión completa de libros** (agregar, buscar, listar)
- ✅ **Registro y administración de usuarios**
- ✅ **Sistema de préstamos** con fechas automáticas (30 días)
- ✅ **Cola de espera** para libros no disponibles
- ✅ **Control de devoluciones** 
- ✅ **Búsqueda eficiente** mediante árboles binarios de búsqueda
- ✅ **Interfaz de terminal** intuitiva y amigable
- ✅ **Códigos autoincrementales** para identificar préstamos

## 🚀 Tecnologías Utilizadas

- **Java**
- **Estructuras de datos**: Árboles binarios de búsqueda (ABB), Colas, Listas, Diccionarios y Conjuntos
- **Java Time API** para manejo de fechas

## 📁 Estructura del Proyecto

```
sistema-biblioteca/
├── src/
│   ├── controlador/
│   │   ├── Sistema.java           # Lógica principal del sistema
│   │   ├── InterfazTerminal.java  # Interfaz de usuario
│   │   └── Main.java              # Punto de entrada (testing)
│   ├── entidades/
│   │   ├── Libro.java             # Entidad Libro
│   │   ├── Usuario.java           # Entidad Usuario
│   │   └── Prestamo.java          # Entidad Préstamo
│   └── tdas/
│       ├── arboles/               # TDAs de ABB y AVL
│       ├── colas/                 # TDAs de Colas dinamicas
│       ├── conjuntos/             # TDAs de Conjuntos estaticos y dinamicos
│       ├── diccionarios/          # TDAs de Diccionarios estaticos
│       └── listas/                # TDAs de Listas Estaticas
└── README.md
```

## 💻 Uso del Sistema

### Menú Principal

Al iniciar el sistema, verás el siguiente menú:

```
─────── SISTEMA DE GESTIÓN BIBLIOTECARIA ───────

┌─────────────── MENÚ PRINCIPAL ───────────────┐
1.  Gestión de Libros                        
2.  Gestión de Usuarios                      
3.  Gestión de Préstamos                    
4.  Consultas y Reportes                     
5.  Visualizar Estructuras de Datos         
0.  Salir                                    
└──────────────────────────────────────────────┘
```

### Operaciones Principales

#### 📖 Gestión de Libros
- Agregar nuevos libros al sistema
- Buscar libros por ISBN
- Listar todos los libros disponibles
- Ver libros ordenados por ISBN

#### 👥 Gestión de Usuarios
- Registrar nuevos usuarios
- Buscar usuarios por DNI
- Listar todos los usuarios
- Ver usuarios ordenados por DNI

#### 📋 Gestión de Préstamos
- Solicitar préstamos (automáticamente calcula devolución a 30 días)
- Realizar devoluciones
- Ver préstamos pendientes
- Consultar cola de espera para libros no disponibles

## 🔑 Funcionalidades Clave

### Sistema de Préstamos Inteligente
- ✅ Fecha de devolución automática (30 días desde hoy)
- ✅ Código único autoincremental para cada préstamo
- ✅ Verificación de disponibilidad de copias
- ✅ Cola de espera cuando no hay copias disponibles
- ✅ Detección de préstamos vencidos

### Búsquedas Eficientes
- ✅ Búsqueda de libros por ISBN: O(log n)
- ✅ Búsqueda de usuarios por DNI: O(log n)
- ✅ Validación de duplicados al agregar

### Reportes y Consultas
- ✅ Listado de todos los préstamos activos
- ✅ Devoluciones pendientes
- ✅ Estado de la cola de espera
- ✅ Visualización de estructuras de datos (árboles)

## 📝 Entidades del Sistema

### Libro
- ISBN (identificador único)
- Título
- Autor
- Cantidad de copias disponibles

### Usuario
- DNI (identificador único)
- Nombre y apellido
- Domicilio
- Teléfono

### Préstamo
- Código autoincremental único
- Libro prestado
- Usuario solicitante
- Fecha de préstamo (automática)
- Fecha de devolución (30 días después)

## 🚧 Mejoras Futuras

- [ ] Interfaz gráfica (GUI)
- [ ] Base de datos para persistencia
- [ ] Sistema de multas por retraso
- [ ] Notificaciones por email
- [ ] Categorización de libros por género
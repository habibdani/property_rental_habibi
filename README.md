# property_rental_habibi
using java springboot
Cara Menjalankan Proyek
1. Prasyarat
Java Version: Proyek ini menggunakan Java 17.
Database: MySQL (Pastikan MySQL sudah terpasang dan berjalan di sistem Anda).
Maven: Pastikan Maven terpasang untuk mengelola dependensi.
2. Membuat Database
Sebelum menjalankan proyek, buat database di MySQL: CREATE DATABASE property_db;
3. Menjalankan Proyek
jalankan proyek menggunakan Maven: mvn spring-boot:run

Daftar API dan Payload
1. Create Property
Endpoint: POST http://localhost:8080/api/properties
Payload:
{
    "name": "Rumah BAGUS",
    "location": "Surabaya",
    "price": 200000000,
    "description": "Rumah modern di Surabaya",
    "status": true
}
2. Get All Properties with Pagination
Endpoint: GET http://localhost:8080/api/properties
Query Params: page (default: 1), size (default: 10)
Contoh Request: GET http://localhost:8080/api/properties?page=1&size=5
3. Get Property by ID
Endpoint: GET http://localhost:8080/api/properties/{id}
Contoh Request: GET http://localhost:8080/api/properties/1
4. Update Property
Endpoint: PUT http://localhost:8080/api/properties/{id}
Payload:
{
    "name": "Rumah Minimalis Tipe Baru",
    "location": "Jakarta",
    "price": 160000000,
    "description": "Rumah minimalis dengan tipe terbaru di Jakarta"
}
5. Delete Property (Soft Delete)
Endpoint: DELETE http://localhost:8080/api/properties/{id}
Contoh Request: DELETE http://localhost:8080/api/properties/1
6. Create Booking
Endpoint: POST http://localhost:8080/api/bookings
Payload:
{
    "propertyId": 1,
    "startDate": "2024-11-01",
    "endDate": "2024-11-10"
}
7. Get All Bookings with Pagination
Endpoint: GET http://localhost:8080/api/bookings
Query Params: page (default: 1), size (default: 10)
Contoh Request: GET http://localhost:8080/api/bookings?page=1&size=5
8. Get Booking by ID
Endpoint: GET http://localhost:8080/api/bookings/{id}
Contoh Request: GET http://localhost:8080/api/bookings/1
9. Update Booking
Endpoint: PUT http://localhost:8080/api/bookings/{id}
Payload:
{
    "propertyId": 1,
    "startDate": "2024-11-01",
    "endDate": "2024-11-12",
    "stato": "COMPLETED"
}
10. Delete Booking (Soft Delete)
Endpoint: DELETE http://localhost:8080/api/bookings/{id}
Contoh Request: DELETE http://localhost:8080/api/bookings/1

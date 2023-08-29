Bootcamp 28 Agustus 2023 - Tugas 3 (Membuat API Universitas WGS menggunakan Java Spring)

**API untuk Jurusan Kuliah**
* `GET http://localhost:8080/majors`: Menampilkan semua list jurusan
* `GET http://localhost:8080/majors/:id`: Menampilkan jurusan berdasarkan ID
* `POST http://localhost:8080/majors`: Membuat jurusan baru
* `PUT http://localhost:8080/majors/:id`: Memperbaharui jurusan
* `DELETE http://localhost:8080/majors/:id`: Menghapus jurusan

**API untuk Mata Kuliah**
* `GET http://localhost:8080/courses`: Menampilkan semua list mata kuliah
* `GET http://localhost:8080/courses/:id`: Menampilkan mata kuliah berdasarkan id
* `POST http://localhost:8080/courses`: Membuat mata kuliah baru
* `PUT http://localhost:8080/courses/:id`: Memperbaharui mata kuliah
* `DELETE http://localhost:8080/courses/:id`: Menghapus mata kuliah

**API untuk Mahasiswa**
* `GET http://localhost:8080/students`: Menampilkan semua list mahasiswa
* `GET http://localhost:8080/students/:npm`: Menampilkan mahasiswa berdasarkan id
* `POST http://localhost:8080/students`: Membuat mahasiswa baru
* `PUT http://localhost:8080/students/:npm`: Memperbaharui mahasiswa
* `DELETE http://localhost:8080/students/:npm`: Menghapus mahasiswa

**API untuk Relasi antara Mahasiswa dan Mata Kuliah**
* `GET http://localhost:8080/grades`: Menampilkan semua list relasi
* `GET http://localhost:8080/grades/:id`: Menampilkan relasi berdasarkan id
* `POST http://localhost:8080/grades`: Membuat mata relasi baru
* `PUT http://localhost:8080/grades/:id`: Memperbaharui relasi
* `DELETE http://localhost:8080/grades/:id`: Menghapus relasi
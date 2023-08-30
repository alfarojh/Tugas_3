Bootcamp 28 Agustus 2023 - Tugas 3 (Membuat API Universitas WGS menggunakan Java Spring)

**API untuk Jurusan Kuliah**
* `GET http://localhost:8080/majors`: Menampilkan semua list jurusan
* `GET http://localhost:8080/majors/:id`: Menampilkan jurusan berdasarkan ID
* `POST http://localhost:8080/majors`: Membuat jurusan baru
* `PUT http://localhost:8080/majors/:id`: Memperbarui data jurusan
* `DELETE http://localhost:8080/majors/:id`: Menghapus jurusan

**API untuk Mata Kuliah**
* `GET http://localhost:8080/courses`: Menampilkan semua list mata kuliah
* `GET http://localhost:8080/courses/:id`: Menampilkan mata kuliah berdasarkan id
* `POST http://localhost:8080/courses`: Membuat mata kuliah baru
* `PUT http://localhost:8080/courses/:id`: Memperbarui data mata kuliah
* `PATCH http://localhost:8080/courses/:id/activated`: Memperbarui status mata kuliah
* `DELETE http://localhost:8080/courses/:id`: Menghapus mata kuliah

**API untuk Mahasiswa**
* `GET http://localhost:8080/students`: Menampilkan semua list mahasiswa
* `GET http://localhost:8080/students/:npm`: Menampilkan mahasiswa berdasarkan id
* `POST http://localhost:8080/students`: Membuat mahasiswa baru
* `PUT http://localhost:8080/students/:npm`: Memperbarui data mahasiswa
* `PATCH http://localhost:8080/students/:npm/activated`: Memperbarui status mahasiswa
* `DELETE http://localhost:8080/students/:npm`: Menghapus mahasiswa

**API untuk Relasi antara Mahasiswa dan Mata Kuliah**
* `GET http://localhost:8080/student-course`: Menampilkan semua list relasi
* `GET http://localhost:8080/student-course/:id`: Menampilkan relasi berdasarkan id
* `POST http://localhost:8080/student-course`: Membuat relasi baru
* `PUT http://localhost:8080/student-course/grade/:id`: Memperbarui nilai ujian dalam relasi
* `PATCH http://localhost:8080/student-course/:id/activated`: Memperbarui status relasi
* `DELETE http://localhost:8080/student-course/:id`: Menghapus relasi

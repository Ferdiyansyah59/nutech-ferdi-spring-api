## Direktori Migrasi Utama

Semua *script* migrasi *database* yang dikelola oleh Flyway disimpan di lokasi standar berikut:

**`src/main/resources/db/migration`**

### Endpoint
```
  https://ferdi-nutech.anitech.id
```
Route List:

- /registration
- /login

### Detail
* **`src/main/resources`**: Ini adalah direktori standar di Spring Boot/Maven/Gradle untuk menyimpan *resources* (sumber daya) aplikasi, termasuk konfigurasi, *template*, dan *script* *database*.
* **`db/migration`**: Ini adalah sub-direktori spesifik yang biasanya diatur secara *default* oleh *library* migrasi *database* (seperti **Flyway**) untuk mencari *script* SQL.

### Cara Kerja
Saat aplikasi dijalankan, *tool* migrasi akan secara otomatis memindai direktori **`db/migration`** untuk menemukan file *script* SQL baru dan menjalankannya sesuai urutan penamaan (misalnya, `V1__init_schema.sql`, `V2__add_users_table.sql`, dll.) untuk memastikan skema *database* selalu terbaru.

---

## 📝 Contoh File Migrasi

Di dalam direktori ini, Anda akan menemukan file dengan format penamaan khusus (misalnya, menggunakan standar Flyway):

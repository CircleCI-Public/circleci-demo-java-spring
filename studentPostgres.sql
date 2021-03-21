-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               PostgreSQL 13.2 (Debian 13.2-1.pgdg100+1) on x86_64-pc-linux-gnu, compiled by gcc (Debian 8.3.0-6) 8.3.0, 64-bit
-- Server Betriebssystem:        
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Exportiere Struktur von Tabelle public.student
CREATE TABLE IF NOT EXISTS "student" (
	"student_id" INTEGER NOT NULL DEFAULT 'nextval(''student_student_id_seq''::regclass)',
	"student_branch" VARCHAR(255) NULL DEFAULT NULL,
	"student_email" VARCHAR(255) NULL DEFAULT NULL,
	"student_name" VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY ("student_id")
);

-- Exportiere Daten aus Tabelle public.student: 0 rows
/*!40000 ALTER TABLE "student" DISABLE KEYS */;
INSERT INTO "student" ("student_id", "student_branch", "student_email", "student_name") VALUES
	(1, 'MCA', 'dolly@gmail.com', 'Dolly'),
	(4, 'MCA', 'dolly@gmail.com', 'Dolly'),
	(6, 'B-Tech', 'sonoo@gmail.com
', 'sonoo'),
	(7, 'B-Tech', 'rai707@email.de', 'RainerB'),
	(3, 'MCA', 'sonoo@gmail.com
', 'sonoo'),
	(8, 'BCA', 'dolly@gmail.com', 'MDolly'),
	(9, 'B-Tech', 'rainier@gmail.com', 'rainier'),
	(10, 'M-Tech', 'ula1983@email.de', 'Ula Habich 29.03.1983 / meet 2015 (18.09.2015),(01.11.2015),(21.12.2015) and 2016 (23.01.2016)');
/*!40000 ALTER TABLE "student" ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

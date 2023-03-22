CREATE DATABASE QuanLyQuanCafeJava
GO
USE QuanLyQuanCafeJava
GO

CREATE TABLE tblDanhMucCafe
(
	maDM VARCHAR(4) NOT NULL PRIMARY KEY,
	tenDM NVARCHAR(50) NOT NULL
)
GO

CREATE TABLE tblCafe
(
	maCafe VARCHAR(5) NOT NULL PRIMARY KEY,
	tenCafe NVARCHAR(50) NOT NULL,
	maDM VARCHAR(4) NOT NULL,
	giaCheBien MONEY NULL,
	giaBan MONEY NOT NULL,
	moTa NVARCHAR(200) NULL,
	thanhPhan NVARCHAR(200) NULL,
	trangThai NVARCHAR(50) NULL DEFAULT N'Đang kinh doanh',
	CONSTRAINT FK_DMCF_maDM FOREIGN KEY(maDM) REFERENCES tblDanhMucCafe(maDM)
	ON UPDATE CASCADE
)
GO
-- ID tăng tự động
CREATE PROCEDURE prIDCafe
	@tenCafe NVARCHAR(50),
	@maDM VARCHAR(4),
	@giaCheBien MONEY,
	@giaBan MONEY,
	@moTa NVARCHAR(200),
	@thanhPhan NVARCHAR(200)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(maCafe) FROM tblCafe) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maCafe, 3)) FROM tblCafe
	SELECT @ID = CASE
		WHEN @ID >= 0 and @ID < 9 THEN ('CF00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
		WHEN @ID >= 9 and @ID < 99 THEN ('CF0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
		WHEN @ID >= 99 and @ID < 999 THEN ('CF' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
	END
	INSERT INTO tblCafe(maCafe, tenCafe, maDM, giaCheBien, giaBan, moTa, thanhPhan)
	VALUES(@ID, @tenCafe, @maDM, @giaCheBien, @giaBan, @moTa, @thanhPhan)
END;
GO

CREATE TABLE tblPhanQuyen
(
	maQuyen VARCHAR(4) NOT NULL PRIMARY KEY,
	quyen NVARCHAR(50) NOT NULL
)
GO

CREATE TABLE tblChucVu
(
	maChucVu VARCHAR(4) NOT NULL PRIMARY KEY,
	tenChucVu NVARCHAR(50) NOT NULL
)
GO

CREATE TABLE tblNhanVien
(
	maNhanVien VARCHAR(5) NOT NULL PRIMARY KEY,
	hoVaTen NVARCHAR(50) NOT NULL,
	gioiTinh NVARCHAR(50) NOT NULL,
	ngaySinh DATE NOT NULL,
	diaChi NVARCHAR(100) NOT NULL,
	soDienThoai VARCHAR(11) NOT NULL UNIQUE,
	email VARCHAR(50) NOT NULL UNIQUE,
	cmnd VARCHAR(15) NOT NULL UNIQUE,
	maChucVu VARCHAR(4) NOT NULL,
	luong MONEY NULL DEFAULT 0,
	taiKhoan VARCHAR(50) NULL,
	matKhau VARCHAR(50) NULL,
	maQuyen VARCHAR(4) NOT NULL,
	trangThai NVARCHAR(50) NULL DEFAULT N'Đang làm việc',
	CONSTRAINT FK_CV_maCV FOREIGN KEY(maChucVu) REFERENCES tblChucVu(maChucVu)
	ON UPDATE CASCADE,
	CONSTRAINT FK_PQ_maPQ FOREIGN KEY(maQuyen) REFERENCES tblPhanQuyen(maQuyen)
	ON UPDATE CASCADE
)
GO

--ID tăng tự động
CREATE PROCEDURE prIDNhanVien
	@hoVaTen NVARCHAR(50),
	@gioiTinh NVARCHAR(50),
	@ngaySinh DATE,
	@diaChi NVARCHAR(100),
	@soDienThoai VARCHAR(11),
	@email VARCHAR(50),
	@cmnd VARCHAR(15),
	@maChucVu VARCHAR(4),
	@luong MONEY,
	@taiKhoan VARCHAR(50),
	@matKhau VARCHAR(50),
	@maQuyen VARCHAR(4)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(maNhanVien) FROM tblNhanVien) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maNhanVien, 3)) FROM tblNhanVien
	SELECT @ID = CASE
		WHEN @ID >= 0 and @ID < 9 THEN ('NV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
		WHEN @ID >= 9 and @ID < 99 THEN ('NV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
		WHEN @ID >= 99 and @ID < 999 THEN ('NV' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
	END
	INSERT INTO tblNhanVien(maNhanVien, hoVaTen, gioiTinh, ngaySinh, diaChi, soDienThoai, email, cmnd, maChucVu, luong, taiKhoan, matKhau, maQuyen)
	VALUES(@ID, @hoVaTen, @gioiTinh, @ngaySinh, @diaChi, @soDienThoai, @email, @cmnd, @maChucVu, @luong, @taiKhoan, @matKhau, @maQuyen)
END;
GO

CREATE TABLE tblBan
(
	maBan VARCHAR(4) NOT NULL PRIMARY KEY,
	tenBan NVARCHAR(10) NOT NULL,
	soCho INT NOT NULL
		CHECK(soCho > 0),
	dangNgoi INT NULL DEFAULT 0,
	CONSTRAINT CK_DN_SC CHECK(dangNgoi <= soCho)
)
GO

CREATE TABLE tblHoaDon
(
	maHoaDon VARCHAR(6) NOT NULL PRIMARY KEY,
	thoiGian DATETIME NULL DEFAULT GETDATE(),
	chietKhau INT NULL DEFAULT 0,
	vat INT NULL DEFAULT 0,
	tienKhachTra MONEY NOT NULL,
	phieuKhach INT NOT NULL,
	tinhTrangDon NVARCHAR(50) NULL DEFAULT N'Đang xử lý',
	maBan VARCHAR(4) NOT NULL,
	maNhanVien VARCHAR(5) NOT NULL,
	CONSTRAINT FK_NV_maNV FOREIGN KEY(maNhanVien) REFERENCES tblNhanVien(maNhanVien)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	CONSTRAINT FK_B_maB FOREIGN KEY(maBan) REFERENCES tblBan(maBan)
	ON DELETE CASCADE
	ON UPDATE CASCADE
)
GO

--ID tăng tự động
CREATE PROCEDURE prIDHoaDon
	@chietKhau INT,
	@vat INT,
	@tienKhachTra MONEY,
	@phieuKhach INT,
	@maBan VARCHAR(4),
	@maNhanVien VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(maHoaDon) FROM tblHoaDon) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maHoaDon, 4)) FROM tblHoaDon
	SELECT @ID = CASE
		WHEN @ID >= 0 and @ID < 9 THEN ('HD000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
		WHEN @ID >= 9 and @ID < 99 THEN ('HD00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
		WHEN @ID >= 99 and @ID < 999 THEN ('HD0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
		WHEN @ID >= 999 and @ID < 9999 THEN ('HD' + CONVERT(CHAR, CONVERT(INT, @ID) + 1))
	END
	INSERT INTO tblHoaDon(maHoaDon, chietKhau, vat, tienKhachTra, phieuKhach, maBan, maNhanVien)
	VALUES(@ID, @chietKhau, @vat, @tienKhachTra, @phieuKhach, @maBan, @maNhanVien)
END;
GO

--Cập nhật đang ngồi, trạng thái của bàn khi thêm, sửa, xóa hóa đơn
--(SELECT COUNT(maHoaDon) FROM tblHoaDon WHERE tinhTrangDon <> N'Đã về')
CREATE TRIGGER trTrangThai_HoaDon
ON tblHoaDon
AFTER INSERT, DELETE, UPDATE
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM deleted)
	BEGIN
		UPDATE tblBan
		SET dangNgoi = dangNgoi + 1
		FROM inserted
		WHERE tblBan.maBan = inserted.maBan
	END
	ELSE
		IF NOT EXISTS (SELECT * FROM inserted)
		BEGIN
			UPDATE tblBan
			SET dangNgoi = (SELECT COUNT(tblHoaDon.maHoaDon) FROM tblHoaDon, deleted WHERE tblHoaDon.tinhTrangDon <> N'Đã về' AND tblHoaDon.maBan = deleted.maBan)
			FROM deleted
			WHERE tblBan.maBan = deleted.maBan
		END
		ELSE
		BEGIN
			UPDATE tblBan
			SET dangNgoi = (SELECT COUNT(tblHoaDon.maHoaDon) FROM tblHoaDon, deleted WHERE tblHoaDon.tinhTrangDon <> N'Đã về' AND tblHoaDon.maBan = deleted.maBan)
			FROM deleted AS d, inserted AS i
			WHERE tblBan.maBan = d.maBan AND tblBan.maBan = i.maBan
		END
	IF EXISTS (SELECT * FROM tblBan WHERE dangNgoi < 0 OR dangNgoi > soCho)
		ROLLBACK
END
GO

CREATE TABLE tblChiTietDonHang
(
	maHoaDon VARCHAR(6) NOT NULL,
	maCafe VARCHAR(5) NOT NULL,
	soLuong INT NULL DEFAULT 1,
	donGia MONEY NOT NULL,
	PRIMARY KEY(maHoaDon, maCafe),
	CONSTRAINT FK_HD_maHD FOREIGN KEY(maHoaDon) REFERENCES tblHoaDon(maHoaDon)
	ON UPDATE CASCADE,
	CONSTRAINT FK_CF_maCafe FOREIGN KEY(maCafe) REFERENCES tblCafe(maCafe)
	ON UPDATE CASCADE
)
GO
--INSERT
INSERT INTO tblDanhMucCafe
VALUES
	('DM01', N'Socola'),
	('DM02', N'Cafe đá xay'),
	('DM03', N'Thạch và topping'),
	('DM04', N'Smoothies'),
	('DM05', N'Cafe Espresso'),
	('DM06', N'Trái cây đá xay')
GO

-- INSERT INTO tblCafe
EXEC prIDCafe N'Socola nóng', N'DM01', 16000, 26000, N'Thuộc nhóm Socola', N'Socola, Cafe';
EXEC prIDCafe N'Oreo đá xay', N'DM01', 20000, 30000, N'Thuộc nhóm Socola', N'Oreo, đá';
EXEC prIDCafe N'Socola đá', N'DM01', 12000, 22000, N'Thuộc nhóm Socola', N'Socola, đá';
EXEC prIDCafe N'Socola cookies', N'DM01', 20000, 30000, N'Thuộc nhóm Socola', N'Socola, cookies';
EXEC prIDCafe N'Cafe đá xay', N'DM02', 20000, 30000, N'Thuộc nhóm Cafe đá xay', N'Cafe, đá';
EXEC prIDCafe N'Cafe caramen', N'DM02', 24000, 34000, N'Thuộc nhóm Cafe đá xay', N'Cafe, caramen';
EXEC prIDCafe N'Cafe tiramisu', N'DM02', 24000, 34000, N'Thuộc nhóm Cafe đá xay', N'Cafe, tiramisu';
EXEC prIDCafe N'Cafe bạc hà', N'DM02', 24000, 34000, N'Thuộc nhóm Cafe đá xay', N'Cafe, bạc hà';
EXEC prIDCafe N'Thạch phô mai', N'DM03', 2000, 3000, N'Thuộc nhóm Thạch và topping', N'Thạch và topping';
EXEC prIDCafe N'Thạch khúc bạch', N'DM03', 2000, 3000, N'Thuộc nhóm Thạch và topping', N'Thạch và topping';
EXEC prIDCafe N'Thạch đào tươi', N'DM03', 2000, 3000, N'Thuộc nhóm Thạch và topping', N'Thạch và topping';
EXEC prIDCafe N'Thạch dừa', N'DM03', 2000, 3000, N'Thuộc nhóm Thạch và topping', N'Thạch và topping';
EXEC prIDCafe N'Smoothies xoài', N'DM04', 20000, 30000, N'Thuộc nhóm Smoothies', N'Smoothies và xoài';
EXEC prIDCafe N'Smoothies đào', N'DM04', 20000, 30000, N'Thuộc nhóm Smoothies', N'Smoothies và đào';
EXEC prIDCafe N'Smoothies dâu tây', N'DM04', 20000, 30000, N'Thuộc nhóm Smoothies', N'Smoothies và dâu tây';
EXEC prIDCafe N'Smoothies việt quất', N'DM04', 22000, 32000, N'Thuộc nhóm Smoothies', N'Smoothies và việt quất';
EXEC prIDCafe N'Cafe sữa', N'DM05', 5000, 15000, N'Thuộc nhóm Cafe Espresso', N'Cafe và sữa';
EXEC prIDCafe N'Mocha nóng', N'DM05', 16000, 26000, N'Thuộc nhóm Cafe Espresso', N'Mocha và sữa';
EXEC prIDCafe N'Bạc xỉu nóng', N'DM05', 16000, 26000, N'Thuộc nhóm Cafe Espresso', N'Bạc xỉu và sữa';
EXEC prIDCafe N'Bạc xỉu đá', N'DM05', 8000, 18000, N'Thuộc nhóm Cafe Espresso', N'Bạc xỉu, đá';
EXEC prIDCafe N'Tắc xí mụi', N'DM06', 20000, 30000, N'Thuộc nhóm Trái cây đá xay', N'Tắc xí mụi';
EXEC prIDCafe N'Chanh tuyết kem tươi', N'DM06', 20000, 30000, N'Thuộc nhóm Trái cây đá xay', N'Chanh tuyết, kem tươi';
EXEC prIDCafe N'Xoài đá xay', N'DM06', 18000, 28000, N'Thuộc nhóm Trái cây đá xay', N'Xoài, đá';
EXEC prIDCafe N'Chanh dây đá xay', N'DM06', 18000, 28000, N'Thuộc nhóm Trái cây đá xay', N'Chanh dây, đá xay';

GO
INSERT INTO tblPhanQuyen
VALUES
	('PQ01', N'Quản lý'),
	('PQ02', N'Nhân viên Order'),
	('PQ03', N'Không được đăng nhập')
GO

INSERT INTO tblChucVu
VALUES
	('CV01', N'Quản lý quán cafe'),
	('CV02', N'Nhân viên order'),
	('CV03', N'Nhân viên phục vụ'),
	('CV04', N'Nhân viên bảo vệ'),
	('CV05', N'Nhân viên pha chế')

GO
SET DATEFORMAT dmy
GO

EXEC prIDNhanVien N'Phạm Công Hoàng', N'Nam', '15/05/1990', N'Bình Hiên, Hải Châu, Đà Nẵng', '0980000001', 'conghoang@gmail.com', '112233445566', 'CV01', 20000000, 'quanly', 'quanly', 'PQ01';
EXEC prIDNhanVien N'Mai Thị Tuyết', N'Nữ', '15/05/1995', N'Bình Hiên, Hải Châu, Đà Nẵng', '0980000002', 'thituyet@gmail.com', '112233445567', 'CV02', 20000000, 'ban', 'ban', 'PQ02';
EXEC prIDNhanVien N'Nguyễn Anh Tuấn', N'Nam', '15/05/1994', N'Bình Hiên, Hải Châu, Đà Nẵng', '0980000003', 'anhtuan@gmail.com', '112233447857', 'CV03', 8000000, '', '', 'PQ03';
EXEC prIDNhanVien N'Nguyễn Thị Kiều', N'Nữ', '15/06/1996', N'Bình Hiên, Hải Châu, Đà Nẵng', '0980000004', 'thikieu@gmail.com', '112233445634', 'CV04', 8000000, '', '', 'PQ03';
EXEC prIDNhanVien N'Nguyễn Hữu Danh', N'Nam', '16/05/1994', N'Bình Hiên, Hải Châu, Đà Nẵng', '0980000005', 'huudanh@gmail.com', '112233445134', 'CV05', 10000000, '', '', 'PQ03';
--(@ID, @chietKhau, @vat, @tienKhachTra, @phieuKhach, @maBan, @maNhanVien)
GO
INSERT INTO tblBan(maBan, tenBan, soCho)
VALUES	('B001', N'Bàn 1', 4),
		('B002', N'Bàn 2', 4),
		('B003', N'Bàn 3', 4),
		('B004', N'Bàn 4', 4),
		('B005', N'Bàn 5', 4),
		('B006', N'Bàn 6', 4),
		('B007', N'Bàn 7', 4),
		('B008', N'Bàn 8', 4),
		('B009', N'Bàn 9', 4),
		('B010', N'Bàn 10', 4),
		('B011', N'Bàn 11', 4),
		('B012', N'Bàn 12', 4),
		('B013', N'Bàn 13', 4),
		('B014', N'Bàn 14', 4),
		('B015', N'Bàn 15', 4),
		('B016', N'Bàn 16', 4),
		('B017', N'Bàn 17', 4),
		('B018', N'Bàn 18', 4),
		('B019', N'Bàn 19', 4),
		('B020', N'Bàn 20', 4),
		('B021', N'Bàn 21', 4)
GO
EXEC prIDHoaDon 1, 2, 100000, 1, 'B001', 'NV002';
EXEC prIDHoaDon 1, 2, 100000, 2, 'B001', 'NV002';
EXEC prIDHoaDon 1, 2, 100000, 3, 'B009', 'NV002';
EXEC prIDHoaDon 1, 2, 100000, 4, 'B001', 'NV002';
EXEC prIDHoaDon 1, 2, 100000, 5, 'B001', 'NV002';
GO
INSERT INTO tblChiTietDonHang(maHoaDon, maCafe, soLuong, donGia)
VALUES	('HD0001', 'CF001', 1, 26000),
		('HD0001', 'CF002', 1, 30000),
		('HD0002', 'CF003', 2, 22000),
		('HD0002', 'CF004', 1, 30000),
		('HD0003', 'CF005', 2, 30000),
		('HD0004', 'CF006', 1, 34000),
		('HD0005', 'CF007', 1, 34000),
		('HD0005', 'CF008', 1, 34000),
		('HD0001', 'CF009', 3, 3000),
		('HD0003', 'CF010', 3, 3000)

/*
GO
SELECT H.maHoaDon, thoiGian, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As N'thanhTien', tinhTrangDon, maKhach
FROM tblChiTietDonHang AS C, tblHoaDon AS H
WHERE C.maHoaDon = H.maHoaDon AND CONVERT(VARCHAR, thoiGian, 103) = CONVERT(VARCHAR, getdate(), 103)
GROUP BY H.maHoaDon, thoiGian, tinhTrangDon, maKhach
GO
SELECT maKhach, SUM(soLuong*donGia) As 'tongCong', chietKhau, vat, tienKhachTra
FROM tblChiTietDonHang, tblHoaDon
WHERE tblChiTietDonHang.maHoaDon = tblHoaDon.maHoaDon AND tblHoaDon.maHoaDon = 1
GROUP BY maKhach, chietKhau, vat, tienKhachTra
GO
SELECT tenCafe, soLuong, donGia, (soLuong*donGia) AS 'thanhTien'
FROM tblChiTietDonHang, tblCafe
WHERE tblChiTietDonHang.maCafe = tblCafe.maCafe AND maHoaDon = 1
GO
SELECT maNhanVien, taiKhoan, matKhau, maQuyen
FROM tblNhanVien
WHERE maQuyen = 'PQ01' OR maQuyen = 'PQ02'
GO
SELECT MAX(maHoaDon) AS 'maHoaDon'
FROM tblHoaDon
GO
SELECT MAX(maCafe) AS 'maHoaDon'
FROM tblCafe
GO
SELECT CF.maCafe, tenCafe, SUM(soLuong*donGia) AS 'doanhThu', SUM(soLuong) AS 'doanhSo'
FROM tblChiTietDonHang AS CT, tblCafe AS CF
WHERE CT.maCafe = CF.maCafe
GROUP BY CF.maCafe, tenCafe
HAVING SUM(soLuong*donGia) IN
(SELECT DISTINCT TOP 10 SUM(soLuong*donGia)
FROM tblChiTietDonhang
GROUP BY maCafe
ORDER BY SUM(soLuong*donGia) DESC)
ORDER BY SUM(soLuong*donGia) DESC;
GO
SELECT C.*
FROM tblCafe AS C, tblDanhMucCafe AS D
WHERE C.maDM = D.maDM AND tenDM = 'Socola'
GO
SELECT HD.maHoaDon, thoiGian, maKhach, tinhTrangDon, hoVaTen, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As N'thanhTien'
FROM tblHoaDon AS HD, tblChiTietDonHang AS CTDH, tblNhanVien AS NV
WHERE HD.maHoaDon = CTDH.maHoaDon AND HD.maNhanVien = NV.maNhanVien
GROUP BY HD.maHoaDon, thoiGian, maKhach, tinhTrangDon, hoVaTen
GO
SELECT maNhanVien, hoVaTen, soDienThoai, tenChucVu, luong
FROM tblNhanVien AS NV, tblChucVu AS CV
WHERE NV.maChucVu = CV.maChucVu
GO
SELECT NV.*, tenChucVu, quyen
FROM tblNhanVien AS NV, tblChucVu AS CV, tblPhanQuyen AS Q
WHERE NV.maChucVu = CV.maChucVu AND NV.maQuyen = Q.maQuyen
GO
SELECT H.maHoaDon, thoiGian, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As N'thanhTien', tinhTrangDon, maKhach
FROM tblChiTietDonHang AS C, tblHoaDon AS H
WHERE C.maHoaDon = H.maHoaDon AND CONVERT(VARCHAR, thoiGian, 103) BETWEEN CONVERT(VARCHAR, '24/05/2021', 103) AND CONVERT(VARCHAR, getdate(), 103)
GROUP BY H.maHoaDon, thoiGian, tinhTrangDon, maKhach

SELECT TOP 1 RIGHT(maHoaDon, 4), maHoaDon
FROM tblHoaDon
ORDER BY RIGHT(maHoaDon, 4) DESC
*/
CREATE DATABASE [Du_an_1]
GO
USE [Du_an_1]
GO

--drop DATABASE [Du_an_1]
-- TABLE Cơ sở chốt
-- *note: TrangThai = 0 --> không hoạt động, TrangThai = 1 --> đang hoạt hoạt động
CREATE TABLE CoSo 
(
	Id			UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ma			VARCHAR(25) UNIQUE,
	Ten			NVARCHAR(255) DEFAULT NULL,
	ViTri		NVARCHAR(255) DEFAULT NULL,
	TrangThai	INT DEFAULT 0
)

-- Table loại hàng
GO
CREATE TABLE LoaiHang
(
	Id		UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ma		VARCHAR(25) UNIQUE,
	Ten		NVARCHAR(255) DEFAULT NULL,
	MoTa	NVARCHAR(255) DEFAULT NULL,
	Gia		DECIMAL(20,0) DEFAULT 0
)

-- Table Khách Hàng
-- *note: GioiTinh = 0 --> Nam , GioiTinh = 1 --> Nữ, GioiTinh = 2 --> Khác
GO
CREATE TABLE KhachHang
(
	Id			UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ma			VARCHAR(25) UNIQUE,
	Ten			NVARCHAR(255) DEFAULT NULL,
	Email		NVARCHAR(255) DEFAULT NULL,
	Sdt			VARCHAR(10) DEFAULT NULL,
	MatKhau		VARCHAR(255) DEFAULT NULL,
	NgaySinh	DATE DEFAULT NULL,
	HinhAnh		VARCHAR(255) DEFAULT NULL,
	GioiTinh	INT DEFAULT 0,
	DiaChi		NVARCHAR(255) DEFAULT NULL
)

-- Table Kho Hàng
-- *note TrangThai = 0 --> Không hoạt động , TrangThai = 1 --> Đang hoạt động
GO
CREATE TABLE KhoHang
(
	Id			UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ma			VARCHAR(25) UNIQUE,
	Ten			NVARCHAR(255) DEFAULT NULL,
	TrangThai	INT DEFAULT 0,
	IdCoSo		UNIQUEIDENTIFIER
)

-- Table Trưởng Phòng
-- *note TrangThai = 0 --> nghỉ tạm thời , TrangThai = 1 --> Đang hoạt động
GO 
CREATE TABLE TruongPhong
(
	Id			UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ma			VARCHAR(25) UNIQUE,
	Ten			NVARCHAR(255) DEFAULT NULL,
	Sdt			VARCHAR(10) DEFAULT NULL,
	Email		NVARCHAR(255) DEFAULT NULL,
	MatKhau		VARCHAR(255) DEFAULT NULL,
	NgaySinh	DATE DEFAULT NULL,
	HinhAnh		VARCHAR(255) DEFAULT NULL,
	GioiTinh	INT DEFAULT 0,
	TrangThai	INT DEFAULT 0,
	IdKhoHang	UNIQUEIDENTIFIER
)

--  Table Nhân Viên
-- *note TrangThai = 0 --> nghỉ tạm thời , TrangThai = 1 --> Đang hoạt động
GO 
CREATE TABLE NhanVienVanDon
(
	Id			UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ma			VARCHAR(25) UNIQUE,
	Ten			NVARCHAR(255) DEFAULT NULL,
	Email		NVARCHAR(255) DEFAULT NULL,
	Sdt			VARCHAR(10) DEFAULT NULL,
	MatKhau		VARCHAR(255) DEFAULT NULL,
	NgaySinh	DATE DEFAULT NULL,
	HinhAnh		VARCHAR(255) DEFAULT NULL,
	GioiTinh	INT DEFAULT 0,
	TrangThai	INT DEFAULT 0,
	IdKhoHang	UNIQUEIDENTIFIER
)

--  Table Nhân Viên giao hàng
-- *note TrangThai = 0 --> đã nghỉ việc , TrangThai = 1 --> Đang tạm nghỉ,
--			TrangThai = 2 --> Đang giao hàng, TrangThai = 3 --> Đang ở kho chính
		--	TrangThai = 4 --> Đang ở kho Giao < chưa nhận hàng j hết
GO 
CREATE TABLE NhanVienGiaoHang
(
	Id			UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ma			VARCHAR(25) UNIQUE,
	Ten			NVARCHAR(255) DEFAULT NULL,
	Email		NVARCHAR(255) DEFAULT NULL,
	Sdt			VARCHAR(10) DEFAULT NULL,
	MatKhau		VARCHAR(255) DEFAULT NULL,
	NgaySinh	DATE DEFAULT NULL,
	HinhAnh		VARCHAR(255) DEFAULT NULL,
	GioiTinh	INT DEFAULT 0,
	TrangThai	INT DEFAULT 0,
	IdKhoHang	UNIQUEIDENTIFIER,
	IdKhoGiao	UNIQUEIDENTIFIER
)
-- Table Đơn hàng
-- *note TrangThai = 0 --> Đang chờ xác nhận,
--		TrangThai = 1 --> Đang giao hàng,
--		TrangThai = 2 --> Giao thành công,
--		TrangThai = 3 --> Đã hủy,
--		TrangThai = 4 --> Hoàn đơn
-- *note HinhThucTT = 0 --> Người nhận,
--		HinhThucTT = 1 --> Người Gửi
GO
CREATE TABLE DonHang
(
	Id				UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	NgayDat			DATE DEFAULT NULL,
	TrangThai		INT DEFAULT 0,
	HinhThucTT		INT DEFAULT 0,
	HinhAnh			VARCHAR(255) DEFAULT NULL,
	TenNguoiNhan	NVARCHAR(255) DEFAULT NULL,
	SdtNguoiNhan	VARCHAR(10) DEFAULT NULL,
	IdLoaiHang		UNIQUEIDENTIFIER,
	IdKhoHangGui	UNIQUEIDENTIFIER,
	IdKhoHangNhan	UNIQUEIDENTIFIER,
	IdKhachHang		UNIQUEIDENTIFIER
)

-- Table Hóa đơn
-- *note TrangThai = 0 --> Chưa thanh toán, TrangThai = 1 --> Đã thanh toán

GO
CREATE TABLE HoaDon
(
	TrangThai		INT DEFAULT 0,
	IdDonHang		UNIQUEIDENTIFIER,
	IdNguoiGiaoHang	UNIQUEIDENTIFIER DEFAULT NULL,
	DonGia			DECIMAL(20,0) DEFAULT 0
	 PRIMARY KEY (IdDonHang, IdNguoiGiaoHang)
)

-- Kho hàng - Cơ sở
ALTER TABLE KhoHang ADD FOREIGN KEY (IdCoSo) REFERENCES CoSo(Id)
-- Trương phòng - Kho Hàng
ALTER TABLE TruongPhong ADD FOREIGN KEY (IdKhoHang) REFERENCES KhoHang(Id)
-- Nhân Viên Vận Đơn - Kho Hàng
ALTER TABLE NhanVienVanDon ADD FOREIGN KEY (IdKhoHang) REFERENCES KhoHang(Id)
-- Nhân Viên Giao Hàng - Kho Hàng
ALTER TABLE NhanVienGiaoHang ADD FOREIGN KEY (IdKhoHang) REFERENCES KhoHang(Id)
-- Nhân Viên Giao Hàng - Kho Hàng
ALTER TABLE NhanVienGiaoHang ADD FOREIGN KEY (IdKhoGiao) REFERENCES KhoHang(Id)
-- DonHang - Loại hàng
ALTER TABLE DonHang ADD FOREIGN KEY (IdLoaiHang) REFERENCES LoaiHang(Id)
-- DonHang - Kho Hàng
ALTER TABLE DonHang ADD FOREIGN KEY (IdKhoHangGui) REFERENCES KhoHang(Id)
-- DonHang - Kho Hàng
ALTER TABLE DonHang ADD FOREIGN KEY (IdKhoHangNhan) REFERENCES KhoHang(Id)
-- DonHang - Khach Hàng
ALTER TABLE DonHang ADD FOREIGN KEY (IdKhachHang) REFERENCES KhachHang(Id)
-- Hóa đơn - Đơn hàng
ALTER TABLE HoaDon ADD FOREIGN KEY (IdDonHang) REFERENCES DonHang(Id)
-- Hóa Đơn - Nhân viên giao hàng
ALTER TABLE HoaDon ADD FOREIGN KEY (IdNguoiGiaoHang) REFERENCES NhanVienGiaoHang(Id)




SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE CoffeeShop;
USE CoffeeShop;


CREATE TABLE `LoaiSP` (
  `MaLSP` VARCHAR(50) NOT NULL,
  `TenLSP` NVARCHAR(150) NOT NULL,
  `MoTa` NVARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `KhachHang` (
  `MaKH` INT NOT NULL,
  `SDT` VARCHAR(13) NOT NULL,
  `UserName` VARCHAR(25) NOT NULL,
  `PassWord` VARCHAR(25) NOT NULL,
  `DiemTL` INT,
  `DiaChi` NVARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `HoaDon` (
  `MaHD` INT NOT NULL,
  `Ngay` VARCHAR(20) NOT NULL,
  `TongTien` INT NOT NULL,
  `GiamGia` INT,
  `DiemTL` INT,
  `ChiPhiKhac` INT,
  `UserName` VARCHAR(25) NOT NULL,
  `SoLuong` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `SanPham` (
  `MaSP` INT NOT NULL,
  `HinhAnh` VARCHAR(500) NOT NULL,
  `GiaSP` INT NOT NULL,
  `TenSP` NVARCHAR(150) NOT NULL,
  `MoTa` NVARCHAR(500) NOT NULL,
  `MaLSP` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `UuDai` (
  `MaUD` INT NOT NULL,
  `TenUD` VARCHAR(50) NOT NULL,
  `HanSD` DATE NOT NULL,
  `GiamGia` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into `LoaiSP` (`MaLSP`, `TenLSP`, `MoTa`) values 
('BN', N'Bánh Ngọt', N'Các loại bánh ngọt và đồ ngọt khác nhau'),
('F', N'Thức ăn', N'Các loại thức ăn khác nhau'),
('TL', N'Thức uống lạnh', N'Những loại đồ uống có đá'),
('TN', N'Thức uống nóng', N'Những loại đồ uống nóng, không đá');

insert into `SanPham` (`MaSP`, `HinhAnh`, `GiaSP`, `TenSP`, `MoTa`, `MaLSP`) values 
(1, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/13.Passion-Fruit-Cheesecake-600x600.jpg', 35000, 'Passion Fruit Cheesecake', N'Bánh phô mai có hương vị ngọt và chua nhẹ của sốt chanh dây', 'BN'),
(2, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/Apple-Cheesecake-600x600.jpg', 35000, 'Apple Crumbled Pie', N'Bánh nhân táo và nho khô thoảng hương quế với vỏ bánh giòn từ bánh quy và phô mai nướng', 'BN'),
(3, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/Blueberry-_-Chocolate-Mousse-Cake-600x600.jpg', 35000, 'Blueberry Mousse Delight', N'Bánh kem mousse mềm mịn ngọt ngào cùng vị chua nhẹ của mứt việt quất', 'BN'),
(4, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/Lemon-Meringue-Cheesecake-600x600.jpg', 35000, 'Cappuccino Cheesecake', N'Bánh phô mai thoảng mùi cà phê giúp tăng hương vị của bánh và lớp đế làm từ bánh bông lan mềm', 'BN'),
(5, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/Red-velvet-cheese-cake-600x600.jpg', 35000, 'Chocolate Fudge', N'Bánh thơm và đậm vị socola đen đặc trưng với lớp kem và cốt bánh bông lan đều được làm từ socola đen', 'BN'),
(6, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/Butter-croissant-600x600.jpg', 35000, 'Ham & Cheese Croissant G&G', N'', 'BN'),
(7, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/2-600x600.jpg', 35000, 'Pandan Kaya', N'Bánh bông lan thơm mùi lá dứa với bề mặt được phủ dừa non giúp tăng hương vị cho bánh', 'BN'),
(8, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/Tiramisu-600x600.png', 35000, 'Tiramisu', N'Bánh phô mai nổi tiếng của Ý với phần kem được làm từ phô mai mascarpone và cốt bánh tẩm cà phê', 'BN'),
(9, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/Coconut-Mousse-Cake-600x600.jpg', 35000, 'Tropical Coconut Delight', N'Bánh bông lan dừa mềm mại, nhẹ nhàng, ít ngọt, dễ tan trong miệng', 'BN'),
(10, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/19.BBQ-Chicken-600x600.jpg', 40000, 'BBQ Chicken', N'Bánh mì Ý mềm hoặc giòn kẹp phi lê gà nướng mè với sốt Nhật kèm theo salad trộn.', 'F'),
(11, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/3.BBQ-Chicken-Salad-600x600.jpg', 40000, 'BBQ Chicken Salad', N'Salad trộn sốt mè Nhật với phi lê gà nướng.', 'F'),
(12, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/14.Break-O-Day-600x600.jpg', 40000, 'Break O Day', N'Món ăn gồm trứng khuấy, xúc xích gà nướng, bánh mì Ý giòn dùng chung với bơ và mứt trái cây kèm theo salad trộn.', 'F'),
(13, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/2.Caesar-Salad-600x600.jpg', 38000, 'Caesar Salad', N'Salad trộn sốt Caesar với trứng luộc, phô mai và bánh mì bơ tỏi sấy giòn.', 'F'),
(14, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/20.Cheese-_-Tomato-600x600.jpg', 40000, 'Cheese & Tomato (vegetarian)', N'Bánh mì Ý mềm hoặc giòn quết bơ tỏi kẹp phô mai nướng và cà chua kèm theo salad trộn. Phù hợp với khách ăn chay.', 'F'),
(15, 'https://coffeebean.com.vn/wp-content/uploads/2017/05/7.Linguine-with-Tomato-600x600.jpg', 40000, 'Linguine with Tomato Sauce (vegetarian)', N'Mỳ Ý sợi dẹt sốt cà chua và phô mai dùng kèm với bánh mì bơ tỏi nướng giòn. Phù hợp với khách ăn chay.', 'F'),
(16, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/BerrydTreasure.png', 50000, 'Berry’d Treasure', N'Thức uống đá xay không cà phê kết hợp của vị berry ngọt lịm và vani, rất phù hợp cho trẻ em.', 'TL'),
(17, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/BlackForest.png', 50000, 'Black Forest', N'Thức uống đá xay kết hợp cà phê tinh chất đặc biệt của CBTL, sữa tách béo cùng hương vị Vani / Socola nâu xay chung với mứt cherry, hạt socola bọc cà phê và kem tươi.', 'TL'),
(18, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/caramel.png', 50000, 'Caramel', N'Thức uống đá xay kết hợp cà phê tinh chất đặc biệt của CBTL, sữa tách béo cùng hương vị Hạt dẻ / Caramel / Socola trắng và kem tươi', 'TL'),
(19, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/DoubleChocolate.png', 50000, 'Double Chocolate', N'Socola đen và sữa tươi lạnh, đậm đà vị socola, không đắng', 'TL'),
(20, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/Hazelnut.png', 50000, 'Hazelnut', N'Thức uống đá xay kết hợp cà phê tinh chất đặc biệt của CBTL, sữa tách béo cùng hương vị Hạt dẻ / Caramel / Socola trắng và kem tươi', 'TL'),
(21, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/MalibuDream-1.png', 50000, 'Malibu Dream', N'Thức uống đá xay không cà phê kết hợp giữa quả dâu tây, chuối với bột vani của Pháp cùng lớp kem phủ trên bề mặt.', 'TL'),
(22, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/theoriginalmocha.png', 50000, 'The Original Mocha', N'Thức uống đá xay kết hợp cà phê tinh chất đặc biệt của CBTL, sữa tách béo cùng hương vị Vani / Socola nâu / Socola đen và kem tươi', 'TL'),
(23, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/theoriginalvanilla.png', 50000, 'The Original Vanilla', N'Thức uống đá xay kết hợp cà phê tinh chất đặc biệt của CBTL, sữa tách béo cùng hương vị Vani / Socola nâu / Socola đen và kem tươi', 'TL'),
(24, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/TheUltimate.png', 50000, 'The Ultimate', N'Thức uống đá xay kết hợp cà phê tinh chất đặc biệt của CBTL, sữa tách béo cùng hương vị Vani / Socola nâu xay chung với hạt socola bọc cà phê espresso và kem tươi.', 'TL'),
(25, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/MatchaGreenTea.png', 50000, N'Trà Xanh Matcha', N'Thức uống đá xay không cà phê kết hợp của sữa tách béo cùng hương trà xanh matcha Nhật Bản và kem tươi.', 'TL'),
(26, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/Capture.jpg', 45000, 'Americano', N'Cà phê (nóng / lạnh) pha loãng kiểu Ý, vị nhẹ nhàng, không đường và không có sữa.', 'TN'),
(27, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/Cappuccino.png', 45000, 'Capuchino', N'Cà phê sữa nóng kiểu Ý, vị cà phê đậm đà, không đường, dùng với sữa tươi và bọt sữa.', 'TN'),
(28, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/CaramelMacchiato.png', 45000, 'Caramel Macchiato', N'Cà phê sữa (nóng / lạnh) kiểu Mỹ, vị cà phê đậm đà dùng với sữa tươi và sốt caramel.', 'TN'),
(29, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/HotChocolate.png', 50000, N'Chocolate Nóng', N'Sữa tươi nóng kết hợp với Socola nâu.', 'TN'),
(30, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/Espresso.png', 50000, 'Espesso', N'Cà phê nóng kiểu Ý, vị cà phê đậm đà, không đường và không có sữa.', 'TN'),
(31, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/Macchiato.png', 45000, 'Macchiato', N'Cà phê nóng kiểu Ý, vị cà phê đậm đà, không đường, dùng với ít sữa nóng và bọt sữa.', 'TN'),
(32, 'https://coffeebean.com.vn/wp-content/uploads/2017/03/HotVanilla.png', 45000, N'Vanilla Nóng', N'Sữa tươi nóng kết hợp với hương Vani.', 'TN');

insert into `KhachHang` (`MaKH`,`SDT`, `UserName`, `PassWord`, `DiemTL`, `DiaChi`) values
(1, '0369573202','donghandsome', '1992002@@', null, N'19 Nguyễn Hữu Thọ, Tân Phong, Quận 7'),
(2, '012345678','kienlun', '123456789', null, N'19 Nguyễn Hữu Thọ, Tân Phong, Quận 7'),
(3, '012345789','gaudan', '234567890', null, N'19 Nguyễn Hữu Thọ, Tân Phong, Quận 7');

insert into `HoaDon` (`MaHD`, `Ngay`, `TongTien`, `GiamGia`, `DiemTL`, `ChiPhiKhac`, `UserName`, `SoLuong`) values
(1, '9/12/2022', 125000, null, null, null, 'donghandsome', 1),
(2, '9/12/2022', 85000, null, null, null, 'kienlun', 1),
(3, '10/12/2002', 95000, null, null, null, 'gaudan', 1);


insert into `UuDai` (`MaUD`, `TenUD`, `HanSD`, `GiamGia`) VALUES
(1, 'Giảm 10k', '9/12/2022', 10000),
(2, 'Giảm 20k', '9/12/2022', 20000),
(3, 'Giảm 30k', '9/12/2022', 30000);


ALTER TABLE `LoaiSP`
  ADD PRIMARY KEY (`MaLSP`);

ALTER TABLE `KhachHang`
  ADD PRIMARY KEY (`MaKH`);

ALTER TABLE `HoaDon`
  ADD PRIMARY KEY (`MaHD`);

ALTER TABLE `SanPham`
  ADD PRIMARY KEY (`MaSP`);

ALTER TABLE `UuDai`
  ADD PRIMARY KEY (`MaUD`);


ALTER TABLE `KhachHang`
  MODIFY `MaKH` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `HoaDon`
  MODIFY `MaHD` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `SanPham`
  MODIFY `MaSP` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

ALTER TABLE `UuDai`
  MODIFY `MaUD` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
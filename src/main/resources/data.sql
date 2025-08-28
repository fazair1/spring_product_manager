-- ----------------------------
-- Records of MstProductCategory
-- ----------------------------
SET IDENTITY_INSERT [dbo].[MstProductCategory] ON
    ;

    INSERT INTO [dbo].[MstProductCategory] ([CreatedBy], [CreatedDate], [IDProductCategory], [ModifiedBy], [ModifiedDate], [Nama]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'1', NULL, NULL, N'Furniture')
    ;

    INSERT INTO [dbo].[MstProductCategory] ([CreatedBy], [CreatedDate], [IDProductCategory], [ModifiedBy], [ModifiedDate], [Nama]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'2', NULL, NULL, N'Elektronik')
    ;

    INSERT INTO [dbo].[MstProductCategory] ([CreatedBy], [CreatedDate], [IDProductCategory], [ModifiedBy], [ModifiedDate], [Nama]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'3', NULL, NULL, N'Alat Tulis')
    ;

    SET IDENTITY_INSERT [dbo].[MstProductCategory] OFF
    ;

-- ----------------------------
-- Records of MstProduct
-- ----------------------------
SET IDENTITY_INSERT [dbo].[MstProduct] ON
    ;

    INSERT INTO [dbo].[MstProduct] ([CreatedBy], [CreatedDate], [IDProduct], [ModifiedBy], [ModifiedDate], [Nama], [IDProductCategory]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'1', NULL, NULL, N'Kursi merah',  N'1')
    ;

    INSERT INTO [dbo].[MstProduct] ( [CreatedBy], [CreatedDate], [IDProduct], [ModifiedBy], [ModifiedDate], [Nama], [IDProductCategory]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'2', NULL, NULL, N'Laptop ACER', N'2')
    ;

    INSERT INTO [dbo].[MstProduct] ( [CreatedBy], [CreatedDate], [IDProduct], [ModifiedBy], [ModifiedDate], [Nama], [IDProductCategory]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'3', NULL, NULL, N'Pensil warna', N'3')
    ;

    INSERT INTO [dbo].[MstProduct] ([CreatedBy], [CreatedDate], [IDProduct], [ModifiedBy], [ModifiedDate], [Nama], [IDProductCategory]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'4', NULL, NULL, N'Kursi biru',  N'1')
    ;

    INSERT INTO [dbo].[MstProduct] ([CreatedBy], [CreatedDate], [IDProduct], [ModifiedBy], [ModifiedDate], [Nama], [IDProductCategory]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'5', NULL, NULL, N'Laptop LENOVO', N'2')
    ;

    INSERT INTO [dbo].[MstProduct] ([CreatedBy], [CreatedDate], [IDProduct], [ModifiedBy], [ModifiedDate], [Nama], [IDProductCategory]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'6', NULL, NULL, N'Pensil 2b', N'3')
    ;

    SET IDENTITY_INSERT [dbo].[MstProduct] OFF
    ;

-- ----------------------------
-- Records of MstWarehouse
-- ----------------------------
SET IDENTITY_INSERT [dbo].[MstWarehouse] ON
    ;

    INSERT INTO [dbo].[MstWarehouse] ([CreatedBy], [CreatedDate], [IDWarehouse], [ModifiedBy], [ModifiedDate], [Nama], [Alamat]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'1', NULL, NULL, N'Gudang A', N'Jakarta')
    ;

    INSERT INTO [dbo].[MstWarehouse] ([CreatedBy], [CreatedDate], [IDWarehouse], [ModifiedBy], [ModifiedDate], [Nama], [Alamat]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'2', NULL, NULL, N'Gudang B', N'Bandung')
    ;

    INSERT INTO [dbo].[MstWarehouse] ([CreatedBy], [CreatedDate], [IDWarehouse], [ModifiedBy], [ModifiedDate], [Nama], [Alamat]) VALUES (N'1', N'2025-03-10 18:32:05.000000', N'3', NULL, NULL, N'Gudang C', N'Surabaya')
    ;

    SET IDENTITY_INSERT [dbo].[MstWarehouse] OFF
    ;

-- ----------------------------
-- Records of MstInventory
-- ----------------------------
SET IDENTITY_INSERT [dbo].[MstInventory] ON
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'1', NULL, NULL, N'1', N'1', N'10')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'2', NULL, NULL, N'2', N'1', N'8')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'3', NULL, NULL, N'3', N'2', N'5')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'4', NULL, NULL, N'4', N'2', N'10')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'5', NULL, NULL, N'5', N'3', N'20')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'6', NULL, NULL, N'6', N'3', N'4')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'7', NULL, NULL, N'2', N'2', N'4')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'8', NULL, NULL, N'3', N'3', N'10')
    ;

    INSERT INTO [dbo].[MstInventory] ([CreatedBy], [CreatedDate], [IDInventory], [ModifiedBy], [ModifiedDate], [IDProduct], [IDWarehouse], [Stock])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', N'9', NULL, NULL, N'4', N'3', N'7')
    ;

    SET IDENTITY_INSERT [dbo].[MstInventory] OFF
    ;

-- ----------------------------
-- Records of MstStatus
-- ----------------------------
SET IDENTITY_INSERT [dbo].[MstStatus] ON
    ;

    INSERT INTO [dbo].[MstStatus] ([CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [IDStatus], [Nama]) VALUES (N'1', N'2025-03-10 18:32:05.000000', NULL, NULL, N'1', N'In Progress')
    ;

    INSERT INTO [dbo].[MstStatus] ([CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [IDStatus], [Nama]) VALUES (N'1', N'2025-03-10 18:32:05.000000', NULL, NULL, N'2', N'Done')
    ;

    INSERT INTO [dbo].[MstStatus] ([CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [IDStatus], [Nama]) VALUES (N'1', N'2025-03-10 18:32:05.000000', NULL, NULL, N'3', N'Cancelled')
    ;

    SET IDENTITY_INSERT [dbo].[MstStatus] OFF
    ;

-- ----------------------------
-- Records of MstTransfer
-- ----------------------------
SET IDENTITY_INSERT [dbo].[MstTransfer] ON
    ;

    INSERT INTO [dbo].[MstTransfer] ([CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [IDTransfer], [IDInventory], [IDWarehouseFrom], [IDWarehouseTo], [Stock], [IDStatus])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', NULL, NULL, N'1', N'1', N'1', N'2', N'5', N'1')
    ;

    INSERT INTO [dbo].[MstTransfer] ([CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [IDTransfer], [IDInventory], [IDWarehouseFrom], [IDWarehouseTo], [Stock], [IDStatus])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', NULL, NULL, N'2', N'2', N'1', N'2', N'7', N'2')
    ;

    INSERT INTO [dbo].[MstTransfer] ([CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [IDTransfer], [IDInventory], [IDWarehouseFrom], [IDWarehouseTo], [Stock], [IDStatus])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', NULL, NULL, N'3', N'5', N'3', N'1', N'5', N'3')
    ;

    INSERT INTO [dbo].[MstTransfer] ([CreatedBy], [CreatedDate], [ModifiedBy], [ModifiedDate], [IDTransfer], [IDInventory], [IDWarehouseFrom], [IDWarehouseTo], [Stock], [IDStatus])
    VALUES (N'1', N'2025-03-10 18:32:05.000000', NULL, NULL, N'4', N'4', N'2', N'3', N'7', N'2')
    ;

    SET IDENTITY_INSERT [dbo].[MstTransfer] OFF
    ;

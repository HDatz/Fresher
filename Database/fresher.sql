-- Tạo cơ sở dữ liệu

USE fresher;

-- Bảng Center (Trung tâm)
CREATE TABLE Center (
    center_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    PRIMARY KEY (`center_id`)
);

-- Bảng Fresher
CREATE TABLE Fresher (
    fresher_id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(100) NOT NULL UNIQUE,  -- Thêm cột username
    password VARCHAR(255) NOT NULL,  
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    programming_language VARCHAR(50),
    center_id INT,
    FOREIGN KEY (center_id) REFERENCES Center(center_id)
);

-- Bảng Project
CREATE TABLE Project (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    center_id INT,
    manager VARCHAR(100),
    start_date DATE,
    end_date DATE,
    language VARCHAR(50),
    status ENUM('NOT_START', 'ONGOING', 'CANCELED', 'CLOSED') NOT NULL,
    FOREIGN KEY (center_id) REFERENCES Center(center_id)
);

-- Bảng Fresher_Project (Liên kết giữa Fresher và Project)
CREATE TABLE Fresher_Project (
    fresher_project_id INT AUTO_INCREMENT PRIMARY KEY,
    fresher_id INT,
    project_id INT,
    FOREIGN KEY (fresher_id) REFERENCES Fresher(fresher_id),
    FOREIGN KEY (project_id) REFERENCES Project(project_id)
);

-- Bảng Scores (Điểm số của Fresher cho từng bài tập)
CREATE TABLE Scores (
    score_id INT AUTO_INCREMENT PRIMARY KEY,
    fresher_id INT,
    assignment1 FLOAT CHECK(assignment1 BETWEEN 0 AND 10),
    assignment2 FLOAT CHECK(assignment2 BETWEEN 0 AND 10),
    assignment3 FLOAT CHECK(assignment3 BETWEEN 0 AND 10),
    final_score FLOAT AS ((assignment1 + assignment2 + assignment3) / 3),
    FOREIGN KEY (fresher_id) REFERENCES Fresher(fresher_id)
);

-- Bảng quản lý Email Logs (Lưu lại các email đã gửi khi thêm/xóa fresher khỏi project)
-- CREATE TABLE Ememail_ (
--     email_log_id INT AUTO_INCREMENT PRIMARY KEY,
--     fresher_id INT,
--     project_id INT,
--     action VARCHAR(50), -- Thêm hoặc Xóa fresher khỏi project
--     email_sent_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (fresher_id) REFERENCES Fresher(fresher_id),
--     FOREIGN KEY (project_id) REFERENCES Project(project_id)
-- );


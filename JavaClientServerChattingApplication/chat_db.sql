-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 01 نوفمبر 2019 الساعة 20:45
-- إصدار الخادم: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chat_db`
--

-- --------------------------------------------------------

--
-- بنية الجدول `groups`
--

CREATE TABLE `groups` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- إرجاع أو استيراد بيانات الجدول `groups`
--

INSERT INTO `groups` (`group_id`, `group_name`) VALUES
(1, 'IT');

-- --------------------------------------------------------

--
-- بنية الجدول `groups_members`
--

CREATE TABLE `groups_members` (
  `member_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- بنية الجدول `messages`
--

CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `deel_id` int(11) NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `message_date` datetime NOT NULL,
  `message_status` int(11) NOT NULL,
  `content_type` int(11) NOT NULL,
  `deel_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- إرجاع أو استيراد بيانات الجدول `messages`
--

INSERT INTO `messages` (`message_id`, `sender_id`, `deel_id`, `content`, `message_date`, `message_status`, `content_type`, `deel_type`) VALUES
(1, 1, 1, '', '2019-07-10 04:14:11', 0, 0, 0),
(2, 1, 1, 'OSAMA', '0000-00-00 00:00:00', 1, 1, 0),
(3, 1, 1, 'ioi', '0000-00-00 00:00:00', 0, 1, 1);

-- --------------------------------------------------------

--
-- بنية الجدول `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ip_address` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- إرجاع أو استيراد بيانات الجدول `users`
--

INSERT INTO `users` (`user_id`, `username`, `ip_address`) VALUES
(1, 'admin', ''),
(3, 'user', ''),
(4, 'xhxtrdtqch', ''),
(5, 'ndqdgppwiy', ''),
(6, 'votjzfjkgf', ''),
(7, 'ciektqjkgi', ''),
(8, 'utehbcedzy', ''),
(9, 'qvhnighdmo', ''),
(10, 'mrdmtceani', ''),
(11, 'mwpubttrda', ''),
(12, 'ufybdmyomd', ''),
(13, 'nkqcdsmhpq', ''),
(14, 'qeoeipzoga', ''),
(15, 'fglnobaeuz', ''),
(16, 'dcrwnydcmn', ''),
(17, 'cnnqxfhllr', ''),
(18, 'tyximabfej', ''),
(19, 'jndpkwsmuv', ''),
(20, 'bjwcmnbxdd', ''),
(21, 'nczvttdltz', ''),
(22, 'twlnmpqwcm', ''),
(23, 'tuuekvfkqj', ''),
(24, 'edqtjkrldr', ''),
(25, 'swmjdblyrf', ''),
(26, 'mszckifinn', ''),
(27, 'hkfrutonpu', ''),
(28, 'imwrkzrohp', ''),
(29, 'gmfkfcmivv', ''),
(30, 'eqrfqijrtl', ''),
(31, 'sepzfmcsvv', '127.0.0.1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`group_id`);

--
-- Indexes for table `groups_members`
--
ALTER TABLE `groups_members`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `deel_id` (`deel_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `groups_members`
--
ALTER TABLE `groups_members`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- قيود الجداول المحفوظة
--

--
-- القيود للجدول `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`deel_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

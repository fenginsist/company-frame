// src/utils/date-test.js
import { formatDate, formatDateStr, str2Date } from './date.js';

/**
 * 如何执行呢
 */

// formatDate 示例
const now = new Date();
console.log(formatDate(now, 'yyyy-MM-dd'));
console.log(formatDate(now, 'yyyy/MM/dd hh:mm:ss'));
console.log(formatDate(now, 'yy-M-d h:m:s'));

// formatDateStr 示例
const dateStr = formatDateStr('2020-02-27T14:43:16.000+0000');
console.log(dateStr);

// str2Date 示例
const date1 = str2Date("2025-04-16");
console.log(date1);
const date2 = str2Date("2025/04/16", "/");
console.log(date2);

// 搭配使用
const d = str2Date("2025-04-16");
const formatted = formatDate(d, 'yyyy年MM月dd日');
console.log(formatted);

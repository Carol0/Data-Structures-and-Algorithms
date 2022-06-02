package com.carol.leetcode;

/**
 * @author Carol
 * @date 2022/5/29
 * @since 1.0.0
 */
public class ValidIPAddress {
    /**
     * 2022-05-29 每日一题 验证IP地址
     * https://leetcode.cn/problems/validate-ip-address/
     * 给定一个字符串queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
     *
     * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中0 <= xi<= 255且xi不能包含 前导零。例如:“192.168.1.1”、 “192.168.1.0” 为有效IPv4地址，
     * “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
     *
     * 一个有效的IPv6地址是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
     *
     * 1 <= xi.length <= 4
     * xi是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
     * 在xi中允许前导零。
     * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，
     * 而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
     */

    public static void main(String[] args) {
        ValidIPAddress validIPAddress = new ValidIPAddress();
        System.out.println(validIPAddress.validIPAddress("172..254.1"));
    }

    /**
     * 思路：根据出现的是.还是:大致判断属于ipv4还是ipv6，然后根据跟则进行判断
     * @param queryIP
     * @return
     */
    public String validIPAddress(String queryIP) {
        String ipv4 = "IPv4";
        String ipv6 = "IPv6";
        String neither = "Neither";
        if (queryIP.contains(".")) {
            if (this.isIpv4(queryIP)) {
                return ipv4;
            }
            return neither;
        }
        if (this.isIpv6(queryIP)) {
            return ipv6;
        }
        return neither;

    }

    /**
     * 判断是否为ipv4
     * 1.用.分割，4个子串xi
     * 2。子串0<=xi<255
     * 3.不能包含前导0
     * @param queryIP
     * @return
     */
    public boolean isIpv4(String queryIP) {
        if (queryIP.charAt(queryIP.length() - 1) == '.' ) {
            return false;
        }
        String[] nums = queryIP.split("\\.");
        if (nums.length != 4) {
            return false;
        }
        for (int i = 0 ; i < nums.length ; i++) {
            if (nums[i].length() < 1 || (nums[i].length() > 1 && '0' == nums[i].charAt(0))) {
                //前导0,如果只有一个0，合法
                return false;
            }
            try {
                int num = Integer.parseInt(nums[i]);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为ipv6
     * 1. :分割，8个xi
     * 2。1<=xi的length<=4
     * 3.仅包含数字或者a到f，A到F（允许有前导0）
     * @param queryIP
     * @return
     */
    public boolean isIpv6(String queryIP) {
        if (queryIP.length() < 1 || queryIP.charAt(queryIP.length() - 1) == ':') {
            return false;
        }
        String[] nums = queryIP.split(":");
        if (nums.length != 8) {
            return false;
        }
        for (String num : nums) {
            if (num.length() < 1 || num.length() > 4) {
                return false;
            }
            for (char ch : num.toCharArray()) {
                if (!((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F'))) {
                    return false;
                }
            }
        }
        return true;
    }

}
package com.internship.demo.mybaties.activemodel;

import org.apache.ibatis.session.SqlSession;

public interface SessionFactory {
  SqlSession openSession();
}

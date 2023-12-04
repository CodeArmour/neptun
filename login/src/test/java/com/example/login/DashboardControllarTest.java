package com.example.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardControllarTest{

    Logger logger = LoggerFactory.getLogger(DashboardControllarTest.class);
    private DatabaseConnection connection;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private DashboardControllar controllar;
    private ObservableList<StudentsData> listData;

    @Before
    public void setUp() throws SQLException{
        connection = mock(DatabaseConnection.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        controllar = new DashboardControllar();
        listData = FXCollections.observableArrayList();

    }

    @Test
    void testStudentsNum(){
        try {
            int expectedCount = 1;
            when(connection.getConnection()).thenReturn(mockConnection);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            controllar.studentsNum();
            verify(mockConnection).prepareStatement(anyString());
            verify(mockPreparedStatement).executeQuery();
            verify(mockResultSet).next();
            verify(mockResultSet).getInt("count(id)");
            when(mockResultSet.getInt("count(id)")).thenReturn(expectedCount);
        } catch (Exception e) {
            logger.error("Exception not expected",e);
        }
    }
    @Test
    void testStudentsNumL() {
        try {
            int expectedCount = 0;
            when(connection.getConnection()).thenReturn(mockConnection);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            controllar.studentsNumL();
            verify(mockConnection).prepareStatement(anyString());
            verify(mockPreparedStatement).executeQuery();
            verify(mockResultSet).next();
            verify(mockResultSet).getInt("count(neptun)");
            when(mockResultSet.getInt("count(id)")).thenReturn(expectedCount);

        } catch (Exception e) {
            logger.error("Exception not expected",e);
        }
    }
    @Test
    void testStudentsNumN(){
        try {
            int expectedCount = 1;
            when(connection.getConnection()).thenReturn(mockConnection);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            controllar.studentsNumN();
            verify(mockConnection).prepareStatement(anyString());
            verify(mockPreparedStatement).executeQuery();
            verify(mockResultSet).next();
            verify(mockResultSet).getInt("count(neptun)");
            when(mockResultSet.getInt("count(id)")).thenReturn(expectedCount);

        } catch (Exception e) {
            logger.error("Exception not expected",e);
        }
    }
    @Test
    public void testAddStudentsListData() throws SQLException {
        try {
            when(connection.getConnection()).thenReturn(mockConnection);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            verify(mockConnection).prepareStatement(anyString());
            verify(mockPreparedStatement).executeQuery();
            verify(mockResultSet).next();
            listData = controllar.addStudentsListData();
            assertNotNull(listData);
            assertEquals(1, listData.size());
            if (!listData.isEmpty()) {
                StudentsData firstStudent = listData.get(0);
                assertEquals("Z0K3AL", firstStudent.getNeptun());
                assertEquals("Rebeka", firstStudent.getFirst_name());
                assertEquals("Boda", firstStudent.getLast_name());
                assertEquals("Female", firstStudent.getGender());
                assertEquals("bodarebeka123@gmail.com", firstStudent.getGmail());
                assertEquals(1, firstStudent.getMissing());
                assertEquals("N", firstStudent.getType());
            }
        } catch (Exception e) {
            logger.error("Exception not expected",e);
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chatsprojects;

/**
 *
 * @author SOMEONE
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class FindUsers extends JFrame {
    //private JTable jTable1;
    private DefaultTableModel model;

    public FindUsers() {

        setTitle("Find Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setLocationRelativeTo(null);

        // Create the table model.
        // Column 0 stores the user ID.
        // Column 1 displays the username.
        // Column 2 displays the Chat button.
        model = new DefaultTableModel(
                new Object[]{"User ID", "Username", "Action"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        // Create the table.
        jTable1 = new JTable(model);
        jTable1.setRowHeight(35);
        jTable1.setFillsViewportHeight(true);

        // Add the button renderer.
        jTable1.getColumnModel()
                .getColumn(2)
                .setCellRenderer(new ButtonRenderer());

        // Add the button editor.
        jTable1.getColumnModel()
                .getColumn(2)
                .setCellEditor(new ButtonEditor());

        // Hide the User ID column from the user.
        jTable1.removeColumn(
                jTable1.getColumnModel().getColumn(0)
        );

        // Add the table to a scroll pane.
        JScrollPane scrollPane = new JScrollPane(jTable1);
        add(scrollPane, BorderLayout.CENTER);

        // Create the refresh button.
        JPanel bottomPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh Users");

        refreshButton.addActionListener(e -> setData());

        bottomPanel.add(refreshButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Load the users from the database.
        setData();
    }

    // Load users from the database.
    public void setData() {

        String sql
                = "SELECT auth_key, username "
                + "FROM usernames "
                + "WHERE auth_key <> ? "
                + "ORDER BY username ASC";

        try (
                 Connection connection = DatabaseConnection.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            // Exclude the currently logged-in user.
            statement.setInt(1, SignIn.myID);

            try ( ResultSet result = statement.executeQuery()) {

                // Clear existing rows before reloading users.
                model.setRowCount(0);

                while (result.next()) {

                    int userId = result.getInt("auth_key");
                    String username = result.getString("username");

                    // Add the user ID, username and Chat button.
                    model.addRow(new Object[]{
                        userId,
                        username,
                        "Chat"
                    });
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load users:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
    
    
    

    // Display the Chat button inside each table row.
    private class ButtonRenderer extends JButton
            implements TableCellRenderer {

        public ButtonRenderer() {
            setText("Chat");
            setFocusable(false);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            setText("Chat");

            return this;
        }
    }

    // Handle clicks on the Chat button.
    private class ButtonEditor extends AbstractCellEditor
            implements TableCellEditor {

        private final JButton button;
        private int selectedRow;

        public ButtonEditor() {

            button = new JButton("Chat");
            button.setFocusable(false);

            button.addActionListener(e -> openChat());
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table,
                Object value,
                boolean isSelected,
                int row,
                int column) {

            // Convert the visible row to the model row.
            selectedRow = table.convertRowIndexToModel(row);

            return button;
        }

        private void openChat() {

            // Get the selected user's ID and name.
            int userId = (int) model.getValueAt(
                    selectedRow, 0
            );

            String username = (String) model.getValueAt(
                    selectedRow, 1
            );

            // Finish editing before opening another window.
            fireEditingStopped();

            try {

                // Open the selected user's chat window.
                SingleChat chatWindow = new SingleChat(userId, username);

                chatWindow.setVisible(true);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        FindUsers.this,
                        "Could not open the chat window:\n"
                        + e.getMessage(),
                        "Chat Error",
                        JOptionPane.ERROR_MESSAGE
                );

                e.printStackTrace();
            }
        }

        @Override
        public Object getCellEditorValue() {
            return "Chat";
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "user name", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
   
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FindUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    new FindUsers().setVisible(true);
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

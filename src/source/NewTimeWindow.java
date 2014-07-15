package source;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abj
 */
public class NewTimeWindow extends javax.swing.JFrame {

    private String[] boatNames;
    public Controller con;
    private int selectedRowIndex, selectedColumIndex;
    private String[][] arr;
    
    public NewTimeWindow(String[] boatNames, Controller con) {
        this.boatNames = boatNames;
        this.con = con;
        String[][]  arr = con.convertBoatList();
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jButton3.setText("Start/Stop tid");
        jButton1.setText("Afslut og gem");
        
        
        DefaultTableModel tableModel = new DefaultTableModel(con.convertBoatList(), new String [] {
                "Nummer", "Navn", "Skipper", "Tid 1", "Tid 2", "Tidsforskel"
            }) {

                @Override
                public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
                }
            };
        jTable2.setModel(tableModel);
        jTable2.getSelectionModel().addSelectionInterval(0, 0);
        
        
        
        jTable2.addKeyListener(new KeyAdapter() {
            @Override
         public void keyPressed(KeyEvent e) {
           int key = e.getKeyCode();
           if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {   
              startStopTime();
              }
           }
         }
      );
       
      
      
        int width = 80;
        for(int i = 0; i < 6; i++){
            int vColIndex = i; 
            TableColumn col = jTable2.getColumnModel().getColumn(vColIndex);
            col.setMinWidth(width);
            col.setMaxWidth(width);
            col.setPreferredWidth(width);  
        }
        
        
       //Add action listener to button
        jButton3.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            
                //Execute when button is pressed
                NewTimeWindow.this.startStopTime();
               
            }
        });
        
        //Add action listener to button
        jButton1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed

                NewTimeWindow.this.con.printTime();
                System.exit(0);
                
            }
        });      
         
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Start/StopTIDEN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        jLabel1.setText("Anders B. Jørgensen");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 482, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(109, 109, 109))))
            .add(layout.createSequentialGroup()
                .add(218, 218, 218)
                .add(jLabel1)
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton1)
                        .add(28, 28, 28))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel1))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //startStopTime();
    }//GEN-LAST:event_jButton3ActionPerformed

   
    public void startStopTime(){
        // T private void startStopTime(int selectedRowIndex){
        selectedRowIndex = jTable2.getSelectedRow();
        boolean b = con.TimeOnBoat(selectedRowIndex);
        if(b){
            //jButton3.setText("Stop tid");
            //jLabel2.setText("Startede tidstagning på " + con.boatList.get(selectedRowIndex).getName() + " - " + con.boatList.get(selectedRowIndex).getCaptain());
            if(con.boatList.get(selectedRowIndex).firstLap())
                jTable2.setValueAt("Kører", selectedRowIndex, 3);
            else
                jTable2.setValueAt("Kører", selectedRowIndex, 4);
            
        }
        if(!b){
            if(con.boatList.get(selectedRowIndex).firstLap()){
                jTable2.setValueAt(con.boatList.get(selectedRowIndex).getTime(0), selectedRowIndex, 3);
            }
            else{
                jTable2.setValueAt(con.boatList.get(selectedRowIndex).getTime(1), selectedRowIndex, 4);
                jTable2.setValueAt(con.boatList.get(selectedRowIndex).findTimeDiff(), selectedRowIndex, 5);
                }
            }
    }

  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}

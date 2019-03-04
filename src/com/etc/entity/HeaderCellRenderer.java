package com.etc.entity;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * ����
 * @author msi
 *
 */
public class HeaderCellRenderer extends DefaultTableCellRenderer

{

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value,

			boolean isSelected, boolean hasFocus, int row, int column)

	{
		JLabel label = new JLabel()
		{
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g)

			{

				// ����jlabel��paintComponent�����������jlabel���ֶ�����

				Graphics2D g2d = (Graphics2D) g;

				g2d.setColor(Color.gray);

				g2d.drawLine(0, 0, this.getWidth(), 0);

				g2d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);

				g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1);

				// һ��Ҫ�ǵõ��ø����paintComponent��������Ȼ��ֻ�Ữ�ߣ�������ʾ����

				super.paintComponent(g);

			}

		};

		label.setText(value != null ? value.toString() : "unknown");

		label.setHorizontalAlignment(JLabel.CENTER);

		return label;

	}

}

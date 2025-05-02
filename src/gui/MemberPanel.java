package gui;

import library.*;
import library.Member;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MemberPanel extends JPanel {
    private DefaultListModel<Member> listModel;
    private JList<Member> memberList;

    public MemberPanel(Library library) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Member Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        memberList = new JList<>(listModel);
        memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(memberList);
        scrollPane.setPreferredSize(new Dimension(500, 250));
        updateMemberList(library.getMembers());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton addButton = new JButton("➕ Add Member");
        JButton removeButton = new JButton("🗑️ Remove Selected");
        JButton searchButton = new JButton("🔍 Search");
        JButton sortNameButton = new JButton("🔤 Sort by Name");
        JButton refreshButton = new JButton("🔁 Show All");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortNameButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter Member Name:");
            if (name != null && !name.trim().isEmpty()) {
                Member m = new Member(name.trim());
                library.addMember(m);
                updateMemberList(library.getMembers());
            }
        });

        removeButton.addActionListener(e -> {
            Member selected = memberList.getSelectedValue();
            if (selected != null) {
                library.removeMember(selected);
                updateMemberList(library.getMembers());
            }
        });

        searchButton.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog("Search member by name:");
            if (keyword != null && !keyword.trim().isEmpty()) {
                updateMemberList(library.searchMembers(keyword.trim()));
            }
        });

        sortNameButton.addActionListener(e -> {
            library.sortMembersByName();
            updateMemberList(library.getMembers());
        });

        refreshButton.addActionListener(e -> {
            updateMemberList(library.getMembers());
        });
    }

    private void updateMemberList(List<Member> members) {
        listModel.clear();
        for (Member m : members) {
            listModel.addElement(m);
        }
    }
}

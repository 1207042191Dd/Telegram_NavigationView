package com.example.telegram;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Gravity;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Map<Integer, Runnable> navigationMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewChats);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista de chats de ejemplo
        List<ChatItem> chatList = new ArrayList<>();
        chatList.add(new ChatItem("Valerie", "What's the matter with you?", "20:58", 5));
        chatList.add(new ChatItem("Vladimir Lenin", "Now you want dialectical design?", "20:57", 0));
        chatList.add(new ChatItem("Julian", "Got any new materials?", "20:54", 1));

        // Configurar Adaptador
        ChatAdapter adapter = new ChatAdapter(chatList);
        recyclerView.setAdapter(adapter);
        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // Configurar DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);

        // Configurar NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        // Inicializar el mapa de navegación
        initializeNavigationMap();
    }

    private void initializeNavigationMap() {
        navigationMap = new HashMap<>();
        navigationMap.put(R.id.nav_new_group, this::showNewGroupDialog);
        navigationMap.put(R.id.nav_secret_chat, () -> navigateToFragment(new SecretChatContactsFragment()));
        navigationMap.put(R.id.nav_new_channel, () -> navigateToFragment(new CreateChannelFragment()));
        navigationMap.put(R.id.nav_contacts, () -> navigateToFragment(new ContactsFragment()));
        navigationMap.put(R.id.nav_calls, () -> navigateToFragment(new CallsHistoryFragment()));
        navigationMap.put(R.id.nav_saved_messages, () -> navigateToFragment(new SavedMessagesFragment()));
        navigationMap.put(R.id.nav_settings, () -> navigateToFragment(new SettingsFragment()));
        navigationMap.put(R.id.nav_invite_friends, this::showInviteFriendsDialog);
        navigationMap.put(R.id.nav_faq, () -> navigateToFragment(new FAQFragment()));
    }

    // Método para manejar las selecciones del NavigationView
    private boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Runnable action = navigationMap.get(menuItem.getItemId());
        if (action != null) {
            action.run();
        } else {
            Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showNewGroupDialog() {
        Toast.makeText(this, "Crear nuevo grupo", Toast.LENGTH_SHORT).show();
        // Aquí implementas el diálogo real si es necesario
    }

    private void showInviteFriendsDialog() {
        Toast.makeText(this, "Invitar amigos", Toast.LENGTH_SHORT).show();
        // Aquí implementas el diálogo real si es necesario
    }

    private void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

// Fragmentos necesarios
class SecretChatContactsFragment extends Fragment {}
class CreateChannelFragment extends Fragment {}
class ContactsFragment extends Fragment {}
class CallsHistoryFragment extends Fragment {}
class SavedMessagesFragment extends Fragment {}
class SettingsFragment extends Fragment {}
class FAQFragment extends Fragment {}

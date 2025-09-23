import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TextInput, Button } from 'react-native';
import React, { useState } from 'react';

export default function App() {
  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [color, setColor] = useState('red');

  // Usuario y contraseña válidos
  const validUser = "koss";
  const validPassword = "1231";

  const handleLogin = () => {
    if (user === validUser && password === validPassword) {
      setMessage("BIENVENIDO ACCESO CONCEDIDO");
      setColor("green");
    } else {
      setMessage("NO PUEDES ENTRAR, ACCESO DENEGADO");
      setColor("red");
    }
  };

  const handleCancel = () => {
    setUser('');
    setPassword('');
    setMessage('');
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.input}
        placeholder="User Name"
        value={user}
        onChangeText={setUser}
      />

      <TextInput
        style={styles.input}
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry={true}
      />

      <View style={styles.buttonContainer}>
        <Button title="LOGIN" onPress={handleLogin} />
        <Button title="CANCEL" onPress={handleCancel} color="gray" />
      </View>

      {message !== '' && (
        <Text style={[styles.message, { color: color }]}>{message}</Text>
      )}

      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
  },
  input: {
    width: '100%',
    height: 40,
    borderWidth: 1,
    borderColor: '#aaa',
    borderRadius: 5,
    paddingHorizontal: 10,
    marginBottom: 12,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    marginBottom: 16,
  },
  message: {
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
    marginTop: 10,
  },
});

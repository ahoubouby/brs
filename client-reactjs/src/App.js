import React from 'react';
import './App.css';
import { args } from './env';

function App() {
    const { port } = args;
    return <div > welcome to BRS is in progress { port } < /div>;
}

export default App;
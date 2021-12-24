import React from 'react'
import { HashRouter, Routes, Route } from 'react-router-dom'
import Home from './pages/home'
import Distribute from './pages/distribute'
import Edit from './pages/edit'

export default function App() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/home" element={<Home />} />
        <Route path="/distribute" element={<Distribute />} />
        <Route path="/edit" element={<Edit />} />
      </Routes>
    </HashRouter>
  )
}

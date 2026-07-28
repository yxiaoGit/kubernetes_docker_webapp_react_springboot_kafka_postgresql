import React, { useState } from 'react';

export default function App() {
  const [productName, setProductName] = useState('');
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!productName.trim()) return;

    setLoading(true);
    setMessage('');

    try {
      const response = await fetch('/api/products', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: productName }),
      });

      if (response.ok) {
        setMessage('✨ Success! Product job sent to Kafka.');
        setProductName('');
      } else {
        setMessage('❌ Failed to create product entry.');
      }
    } catch (error) {
      setMessage('❌ Connection to API Gateway failed.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: '420px', margin: '60px auto', fontFamily: 'system-ui, sans-serif', textAlign: 'center', padding: '20px', border: '1px solid #eaeaea', borderRadius: '8px', boxShadow: '0 4px 12px rgba(0,0,0,0.05)' }}>
      <h2 style={{ color: '#111' }}>📦 Product Ingestion Engine</h2>
      <p style={{ fontSize: '14px', color: '#666' }}>Connected over Secure TLS Ingress Tunnel</p>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '12px', marginTop: '20px' }}>
        <input
          type="text"
          placeholder="Product Name (e.g. Mechanical Keyboard)"
          value={productName}
          onChange={(e) => setProductName(e.target.value)}
          disabled={loading}
          style={{ padding: '12px', fontSize: '15px', borderRadius: '6px', border: '1px solid #ccc', outline: 'none' }}
        />
        <button 
          type="submit" 
          disabled={loading}
          style={{ padding: '12px', fontSize: '15px', backgroundColor: '#0070f3', color: 'white', border: 'none', borderRadius: '6px', cursor: 'pointer', fontWeight: 'bold' }}
        >
          {loading ? 'Processing Interconnect...' : 'Publish Creation Request'}
        </button>
      </form>
      {message && <p style={{ marginTop: '20px', fontSize: '14px', color: '#333' }}>{message}</p>}
    </div>
  );
}
